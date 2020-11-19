package com.redveloper.core.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.redveloper.core.data.source.local.entity.GameKeys

@Dao
abstract class GameKeysDao : BaseDao<GameKeys> {
    @Query("SELECT * FROM gamekeys WHERE gameId = :id")
    abstract suspend fun gameKeysById(id: Int) : GameKeys?

    @Query("DELETE FROM gamekeys")
    abstract suspend fun clearGameKeys()
}