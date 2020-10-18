package com.redveloper.core.network.game

import com.google.gson.annotations.SerializedName

data class RootGameResponse (
    @field:SerializedName("count")
    val count : Int,
    @field:SerializedName("next")
    val next : String,
    @field:SerializedName("previous")
    val previous: String,
    @field:SerializedName("results")
    val results: List<GameResponse>
)