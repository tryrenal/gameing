package com.redveloper.core.network.game

import com.google.gson.annotations.SerializedName

data class ShortScreenshotResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("image")
    val image: String
)