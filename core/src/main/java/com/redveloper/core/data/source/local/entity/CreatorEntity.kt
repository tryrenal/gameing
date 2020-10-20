package com.redveloper.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CreatorEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "slug")
    val slug: String?,
    @ColumnInfo(name = "image")
    val image: String?,
    @ColumnInfo(name = "imageBackground")
    val imageBackgroudn: String?,
    @ColumnInfo(name = "gamesCount")
    val gamesCount: Int?
)