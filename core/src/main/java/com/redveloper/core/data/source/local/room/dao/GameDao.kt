package com.redveloper.core.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.redveloper.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class GameDao: BaseDao<GameEntity> {

    @Query("SELECT * FROM gameentity")
    abstract fun getAllGame() : Flow<List<GameEntity>>
}