package com.redveloper.creator.core.data.mediator

import androidx.paging.PagingState
import com.redveloper.core.data.source.local.entity.CreatorEntity
import com.redveloper.core.data.source.local.entity.CreatorKeys
import com.redveloper.core.data.source.local.room.AppDatabase

class CreatorRemoteKey (
    private val appDatabase: AppDatabase
) {
    suspend fun getLastRemoteKey(state: PagingState<Int, CreatorEntity>): CreatorKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { data -> appDatabase.creatorKeysDao().creatorKeysId(data.id) }
    }

    suspend fun getFirstRemoteKey(state: PagingState<Int, CreatorEntity>): CreatorKeys? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { data -> appDatabase.creatorKeysDao().creatorKeysId(data.id) }
    }

    suspend fun getClosestRemoteKey(state: PagingState<Int, CreatorEntity>): CreatorKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { creatorId ->
                appDatabase.creatorKeysDao().creatorKeysId(creatorId)
            }
        }
    }
}