package com.redveloper.home.core.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.redveloper.core.data.source.local.entity.GameEntity
import com.redveloper.core.data.source.local.entity.GameKeys
import com.redveloper.core.data.source.local.room.AppDatabase
import com.redveloper.core.data.source.remote.network.ApiService
import com.redveloper.home.core.utils.GameMapper
import java.io.IOException
import java.io.InvalidObjectException

@OptIn(ExperimentalPagingApi::class)
class GameMediator (val apiService: ApiService, val appDatabase: AppDatabase) : RemoteMediator<Int, GameEntity>(){
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GameEntity>
    ): MediatorResult {
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
            val response = apiService.getAllGames(page)
            val isEndOflist = response.results.isEmpty()
            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH){
                    appDatabase.gameKeysDao().clearGameKeys()
                    appDatabase.gameDao().clearDataGame()
                }
                val prevKeys = if (page == 1) null else page - 1
                val nextKeys = if (isEndOflist) null else page + 1
                val keys = response.results.map {
                    GameKeys(gameId = it.id, prevKey = prevKeys, nextKey = nextKeys)
                }
                appDatabase.gameKeysDao().insertList(keys)
                val entity = GameMapper.responseToEntityList(response.results)
                appDatabase.gameDao().insertList(entity)
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOflist)
        } catch (e: IOException){
            return MediatorResult.Error(e)
        } catch (e: Exception){
            return MediatorResult.Error(e)
        }
    }

    suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, GameEntity>) : Any? {
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

    private suspend fun getLastRemoteKey(state: PagingState<Int, GameEntity>) : GameKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { data -> appDatabase.gameKeysDao().gameKeysById(data.id) }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, GameEntity>) : GameKeys? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { data -> appDatabase.gameKeysDao().gameKeysById(data.id) }
    }

    private suspend fun getClosestRemoteKey(state: PagingState<Int, GameEntity>) : GameKeys? {
        return state.anchorPosition?.let {position ->
            state.closestItemToPosition(position)?.id?.let { gameId ->
                appDatabase.gameKeysDao().gameKeysById(gameId)
            }
        }
    }
}