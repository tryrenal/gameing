package com.redveloper.core.data.source.remote.response.creator

import com.google.gson.annotations.SerializedName

data class CreatorGameResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("slug")
    val slug: String?,
    @field:SerializedName("name")
    val name: String?,
    @field:SerializedName("added")
    val added: Int?
)