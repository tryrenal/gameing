package com.redveloper.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.redveloper.core.data.source.local.entity.CreatorEntity
import com.redveloper.core.data.source.local.entity.CreatorKeys
import com.redveloper.core.data.source.local.entity.GameEntity
import com.redveloper.core.data.source.local.entity.GameKeys
import com.redveloper.core.data.source.local.room.dao.CreatorDao
import com.redveloper.core.data.source.local.room.dao.CreatorKeysDao
import com.redveloper.core.data.source.local.room.dao.GameDao
import com.redveloper.core.data.source.local.room.dao.GameKeysDao

@Database(
    entities = [GameEntity::class, CreatorEntity::class, CreatorKeys::class, GameKeys::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun gameDao() : GameDao
    abstract fun gameKeysDao() : GameKeysDao
    abstract fun creatorDao() : CreatorDao
    abstract fun creatorKeysDao() : CreatorKeysDao
}