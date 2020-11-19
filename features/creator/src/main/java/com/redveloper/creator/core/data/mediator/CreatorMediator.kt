package com.redveloper.creator.core.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.bumptech.glide.load.HttpException
import com.redveloper.core.data.source.local.LocalDataSource
import com.redveloper.core.data.source.local.entity.CreatorEntity
import com.redveloper.core.data.source.local.entity.CreatorKeys
import com.redveloper.core.data.source.local.room.AppDatabase
import com.redveloper.core.data.source.remote.RemoteDataSource
import com.redveloper.core.data.source.remote.network.ApiService
import com.redveloper.creator.core.utils.CreatorMapper
import java.io.IOException
import java.io.InvalidObjectException

@ExperimentalPagingApi
class CreatorMediator (
    private val remoteDataSource: RemoteDataSource,
    private val appDatabase: AppDatabase,
    private val localDataSource: LocalDataSource
): RemoteMediator<Int, CreatorEntity>(){
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
            val response = remoteDataSource.getAllCreator(page)
            val isEndOfList = response.isEmpty()
            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH){
                    localDataSource.clearCreatorKeys()
                    localDataSource.clearDataCreator()
                }
                val prevKey = if (page == 1) null  else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = response.map {
                    CreatorKeys(creatorId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                localDataSource.insertKeyCreator(keys)
                val entity = CreatorMapper.responseToEntity(response)
                localDataSource.insertCreator(entity)
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