package com.redveloper.core.data.source.remote.response.creator

import com.google.gson.annotations.SerializedName

data class RootCreatorResponse(
    @field:SerializedName("count")
    val count: Int,
    @field:SerializedName("next")
    val next: String?,
    @field:SerializedName("previous")
    val previous: String?,
    @field:SerializedName("results")
    val results: List<CreatorResponse>
)