package com.redveloper.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.redveloper.core.data.source.local.entity.CreatorEntity
import com.redveloper.core.data.source.local.entity.GameEntity
import com.redveloper.core.data.source.local.entity.CreatorKeys
import com.redveloper.core.data.source.local.room.dao.CreatorDao
import com.redveloper.core.data.source.local.room.dao.GameDao
import com.redveloper.core.data.source.local.room.dao.CreatorKeysDao

@Database(
    entities = [GameEntity::class, CreatorEntity::class, CreatorKeys::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun GameDao() : GameDao
    abstract fun CreatorDao() : CreatorDao
    abstract fun CreatorKeysDao() : CreatorKeysDao
}