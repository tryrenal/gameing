package com.redveloper.core.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.redveloper.core.data.source.local.entity.CreatorKeys

@Dao
interface CreatorKeysDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(creatorKeys: List<CreatorKeys>)

    @Query("SELECT * FROM CreatorKeys WHERE gameId = :id")
    suspend fun gameKeysById(id: Int): CreatorKeys?

    @Query("DELETE FROM creatorkeys")
    suspend fun clearCreatorKeys()
}