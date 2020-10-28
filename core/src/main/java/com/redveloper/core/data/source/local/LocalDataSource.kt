package com.redveloper.core.data.source.local

import androidx.paging.DataSource
import androidx.paging.PagingSource
import com.redveloper.core.data.source.local.entity.CreatorEntity
import com.redveloper.core.data.source.local.entity.GameEntity
import com.redveloper.core.data.source.local.room.dao.CreatorDao
import com.redveloper.core.data.source.local.room.dao.GameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource (
    private val gameDao: GameDao,
    private val creatorDao: CreatorDao
) {
    //game
    fun getAllGame() : PagingSource<Int, GameEntity> = gameDao.getAllGame()
    suspend fun insertGame(data: List<GameEntity>) = gameDao.insert(data)

    //creator
    fun getAllCreator() : Flow<List<CreatorEntity>> = creatorDao.getAllCreator()
    suspend fun insertCreator(data: List<CreatorEntity>) = creatorDao.insert(data)
}