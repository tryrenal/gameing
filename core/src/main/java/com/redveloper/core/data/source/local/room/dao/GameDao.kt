package com.redveloper.core.data.source.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.redveloper.core.data.source.local.entity.GameEntity

@Dao
abstract class GameDao : BaseDao<GameEntity> {

    @Query("SELECT * FROM gameentity ORDER BY id COLLATE NOCASE ASC")
    abstract fun getAllGame(): PagingSource<Int, GameEntity>
}