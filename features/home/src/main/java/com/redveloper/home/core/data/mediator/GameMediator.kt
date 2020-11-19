package com.redveloper.home.core.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.redveloper.core.data.source.local.LocalDataSource
import com.redveloper.core.data.source.local.entity.GameEntity
import com.redveloper.core.data.source.local.entity.GameKeys
import com.redveloper.core.data.source.local.room.AppDatabase
import com.redveloper.core.data.source.remote.RemoteDataSource
import com.redveloper.core.data.source.remote.network.ApiService
import com.redveloper.home.core.utils.GameMapper
import java.io.IOException
import java.io.InvalidObjectException

@OptIn(ExperimentalPagingApi::class)
class GameMediator(
    private val remoteDataSource: RemoteDataSource,
    private val appDatabase: AppDatabase,
    private val localDataSource: LocalDataSource
) : RemoteMediator<Int, GameEntity>(){

    private val gameRemoteKey =
        GameRemoteKey(localDataSource)

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GameEntity>
    ): MediatorResult {
        val page = when(val pagedKeyData = getKeyPageData(loadType, state)){
            is MediatorResult.Success -> {
                return pagedKeyData
            }
            else -> {
                pagedKeyData as Int
            }
        }

        try {
            val response = remoteDataSource.getAllGames(page)
            val isEndOflist = response.isEmpty()
            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH){
                    localDataSource.clearGameKeys()
                    localDataSource.clearDataGame()
                }
                val prevKeys = if (page == 1) null else page - 1
                val nextKeys = if (isEndOflist) null else page + 1
                val keys = response.map {
                    GameKeys(gameId = it.id, prevKey = prevKeys, nextKey = nextKeys)
                }
                localDataSource.insertKeyGame(keys)
                val entity = GameMapper.responseToEntityList(response)
                localDataSource.insertGame(entity)
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOflist)
        } catch (e: IOException){
            return MediatorResult.Error(e)
        } catch (e: Exception){
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, GameEntity>) : Any? {
        return when(loadType){
            LoadType.REFRESH -> {
                val remoteKeys = gameRemoteKey.getClosestRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.APPEND -> {
                val remoteKeys = gameRemoteKey.getLastRemoteKey(state)
                    ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                remoteKeys.nextKey
            }
            LoadType.PREPEND -> {
                val remoteKeys = gameRemoteKey.getFirstRemoteKey(state)
                    ?: throw InvalidObjectException("Invalid state, key should not be null")
                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                remoteKeys.prevKey
            }
        }
    }
}