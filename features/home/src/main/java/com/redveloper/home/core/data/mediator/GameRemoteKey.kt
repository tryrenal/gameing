package com.redveloper.home.core.data.mediator

import androidx.paging.PagingState
import com.redveloper.core.data.source.local.entity.GameEntity
import com.redveloper.core.data.source.local.entity.GameKeys
import com.redveloper.core.data.source.local.room.AppDatabase

class GameRemoteKey(
    private val appDatabase: AppDatabase
) {

    suspend fun getLastRemoteKey(state: PagingState<Int, GameEntity>) : GameKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { data -> appDatabase.gameKeysDao().gameKeysById(data.id) }
    }

    suspend fun getFirstRemoteKey(state: PagingState<Int, GameEntity>) : GameKeys? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { data -> appDatabase.gameKeysDao().gameKeysById(data.id) }
    }

    suspend fun getClosestRemoteKey(state: PagingState<Int, GameEntity>) : GameKeys? {
        return state.anchorPosition?.let {position ->
            state.closestItemToPosition(position)?.id?.let { gameId ->
                appDatabase.gameKeysDao().gameKeysById(gameId)
            }
        }
    }
}