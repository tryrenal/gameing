package com.redveloper.core.data.source.remote.response.game

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @field:SerializedName("id")
    val id : Int,
    @field:SerializedName("slug")
    val slug: String?,
    @field:SerializedName("name")
    val name: String?,
    @field:SerializedName("released")
    val released: String?,
    @field:SerializedName("background_image")
    val background_image: String?,
    @field:SerializedName("rating")
    val rating:Double?,
    @field:SerializedName("rating_top")
    val ratingTop: Int?,
    @field:SerializedName("playtime")
    val playtime: Int?,
    @field:SerializedName("genres")
    val genres : List<GenreResponse>,
    @field:SerializedName("short_screenshots")
    val shortScreenshots: List<ShortScreenshotResponse>
)