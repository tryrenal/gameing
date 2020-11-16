package com.redveloper.creator.core.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.bumptech.glide.load.HttpException
import com.redveloper.core.data.source.local.entity.CreatorEntity
import com.redveloper.core.data.source.local.entity.CreatorKeys
import com.redveloper.core.data.source.local.room.AppDatabase
import com.redveloper.core.data.source.remote.network.ApiService
import com.redveloper.creator.core.utils.CreatorMapper
import java.io.IOException
import java.io.InvalidObjectException

@ExperimentalPagingApi
class CreatorMediator (val apiService: ApiService, val appDatabase: AppDatabase): RemoteMediator<Int, CreatorEntity>(){
    override suspend fun load(loadType: LoadType, state: PagingState<Int, CreatorEntity>): MediatorResult {
        val pagedKeyData = getKeyPageData(loadType, state)
        val page = when(pagedKeyData){
            is MediatorResult.Success -> {
                return pagedKeyData
            }
            else -> {
                pagedKeyData as Int
            }
        }

        try {
            val response = apiService.getAllCreator(page)
            val isEndOfList = response.results.isEmpty()
            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH){
                    appDatabase.CreatorKeysDao().clearCreatorKeys()
                    appDatabase.CreatorDao().clearCreatorData()
                }
                val prevKey = if (page == 1) null  else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = response.results.map { 
                    CreatorKeys(gameId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                appDatabase.CreatorKeysDao().insertAll(keys)
                val entity = CreatorMapper.responseToEntity(response.results)
                appDatabase.CreatorDao().insertList(entity)
            }

            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (e: IOException){
            return MediatorResult.Error(e)
        } catch (e: HttpException){
            return MediatorResult.Error(e)
        }
    }

    suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, CreatorEntity>) : Any? {
        return when(loadType){
            LoadType.REFRESH -> {
                val remoteKeys = getClosestRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                    ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                remoteKeys.nextKey
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                    ?: throw InvalidObjectException("Invalid state, key should not be null")
                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                remoteKeys.prevKey
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, CreatorEntity>): CreatorKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { data -> appDatabase.CreatorKeysDao().gameKeysById(data.id) }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, CreatorEntity>): CreatorKeys? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { data -> appDatabase.CreatorKeysDao().gameKeysById(data.id) }
    }

    private suspend fun getClosestRemoteKey(state: PagingState<Int, CreatorEntity>): CreatorKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { gameId ->
                appDatabase.CreatorKeysDao().gameKeysById(gameId)
            }
        }
    }
}