package com.redveloper.core.data.source.local

import androidx.paging.PagingSource
import com.redveloper.core.data.source.local.entity.CreatorEntity
import com.redveloper.core.data.source.local.entity.GameEntity
import com.redveloper.core.data.source.local.room.dao.CreatorDao
import com.redveloper.core.data.source.local.room.dao.GameDao
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class LocalDataSource (
    private val gameDao: GameDao,
    private val creatorDao: CreatorDao
) {
    //game
    fun getAllGame() : PagingSource<Int, GameEntity> = gameDao.getAllGame()
    fun getGameById(id: Int) : Flow<GameEntity> = gameDao.getGameById(id)
    suspend fun insertGame(data: List<GameEntity>) = gameDao.insertList(data)
    fun updateGame(data: GameEntity) = gameDao.update(data)
    fun getFavoriteGame() : PagingSource<Int, GameEntity> = gameDao.getFavoriteGame()
    fun setFavoritGame(data: GameEntity, state: Boolean) {
        Timber.i(state.toString())
        data.isFavorit = state
        gameDao.update(data)
    }

    //creator
    fun getAllCreator() : PagingSource<Int, CreatorEntity> = creatorDao.getAllCreator()
    suspend fun insertCreator(data: List<CreatorEntity>) = creatorDao.insertList(data)
}