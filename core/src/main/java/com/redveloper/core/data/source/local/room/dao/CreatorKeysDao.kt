package com.redveloper.core.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.redveloper.core.data.source.local.entity.CreatorKeys

@Dao
abstract class CreatorKeysDao : BaseDao<CreatorKeys>{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(creatorKeys: List<CreatorKeys>)

    @Query("SELECT * FROM CreatorKeys WHERE creatorId = :id")
    abstract suspend fun creatorKeysId(id: Int): CreatorKeys?

    @Query("DELETE FROM creatorkeys")
    abstract suspend fun clearCreatorKeys()
}