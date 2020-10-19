package com.redveloper.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.redveloper.core.data.source.local.entity.GameEntity
import com.redveloper.core.data.source.local.room.dao.GameDao

@Database(
    entities = [GameEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun GameDao() : GameDao
}