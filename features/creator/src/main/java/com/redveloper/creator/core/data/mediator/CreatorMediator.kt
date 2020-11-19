package com.redveloper.creator.core.data.mediator

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
class CreatorMediator (private val apiService: ApiService, private val appDatabase: AppDatabase): RemoteMediator<Int, CreatorEntity>(){
    private val creatorRemoteKey  = CreatorRemoteKey(appDatabase)

    override suspend fun load(loadType: LoadType, state: PagingState<Int, CreatorEntity>): MediatorResult {
        val page = when(val pagedKeyData = getKeyPageData(loadType, state)){
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
                    appDatabase.creatorKeysDao().clearCreatorKeys()
                    appDatabase.creatorDao().clearCreatorData()
                }
                val prevKey = if (page == 1) null  else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = response.results.map { 
                    CreatorKeys(creatorId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                appDatabase.creatorKeysDao().insertAll(keys)
                val entity = CreatorMapper.responseToEntity(response.results)
                appDatabase.creatorDao().insertList(entity)
            }

            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (e: IOException){
            return MediatorResult.Error(e)
        } catch (e: HttpException){
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, CreatorEntity>) : Any? {
        return when(loadType){
            LoadType.REFRESH -> {
                val remoteKeys = creatorRemoteKey.getClosestRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.APPEND -> {
                val remoteKeys = creatorRemoteKey.getLastRemoteKey(state)
                    ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                remoteKeys.nextKey
            }
            LoadType.PREPEND -> {
                val remoteKeys = creatorRemoteKey.getFirstRemoteKey(state)
                    ?: throw InvalidObjectException("Invalid state, key should not be null")
                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                remoteKeys.prevKey
            }
        }
    }

}