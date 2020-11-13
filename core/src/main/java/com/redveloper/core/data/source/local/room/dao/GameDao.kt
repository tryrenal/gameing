package com.redveloper.core.data.source.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.redveloper.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class GameDao : BaseDao<GameEntity> {

    @Query("SELECT * FROM gameentity ORDER BY id COLLATE NOCASE ASC")
    abstract fun getAllGame(): PagingSource<Int, GameEntity>

    @Query("SELECT * FROM gameentity WHERE id = :id LIMIT 1")
    abstract fun getGameById(id: Int): Flow<GameEntity>

    @Query("SELECT * FROM gameentity WHERE isFavorit = 1")
    abstract fun getFavoriteGame() : PagingSource<Int, GameEntity>
}