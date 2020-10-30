package com.redveloper.core.data.source.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.redveloper.core.data.source.local.entity.CreatorEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CreatorDao : BaseDao<CreatorEntity> {

    @Query("SELECT * FROM creatorentity")
    abstract fun getAllCreator(): PagingSource<Int, CreatorEntity>
}