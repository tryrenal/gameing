package com.redveloper.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameEntity (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "slug")
    val slug: String?,
    @ColumnInfo(name = "released")
    val released: String?,
    @ColumnInfo(name = "background_image")
    val backgroundImage: String?,
    @ColumnInfo(name = "rating")
    val rating: Double?,
    @ColumnInfo(name = "rating_top")
    val rating_top: Int?,
    @ColumnInfo(name = "isFavorit")
    val isFavorit: Boolean = false
)