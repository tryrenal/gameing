package com.redveloper.core.data.source.local.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj : T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(obj : List<T>)

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)
}