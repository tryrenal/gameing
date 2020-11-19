package com.redveloper.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameKeys(@PrimaryKey val gameId: Int, val prevKey: Int?, val nextKey: Int?)