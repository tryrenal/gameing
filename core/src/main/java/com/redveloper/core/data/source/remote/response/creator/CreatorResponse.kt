package com.redveloper.core.data.source.remote.response.creator

import com.google.gson.annotations.SerializedName

data class CreatorResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("slug")
    val slug: String?,
    @field:SerializedName("image")
    val image: String?,
    @field:SerializedName("image_background")
    val imageBackground: String?,
    @field:SerializedName("games_count")
    val gamesCount: Int?,
    @field:SerializedName("games")
    val games: List<CreatorGameResponse>
)