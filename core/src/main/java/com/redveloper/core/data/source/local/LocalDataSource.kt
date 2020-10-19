package com.redveloper.core.data.source.local

import com.redveloper.core.data.source.local.entity.GameEntity
import com.redveloper.core.data.source.local.room.dao.GameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource (
    private val gameDao: GameDao
) {
    //game
    fun getAllGame() : Flow<List<GameEntity>> = gameDao.getAllGame()
    suspend fun insertGame(data: List<GameEntity>) = gameDao.insert(data)
}