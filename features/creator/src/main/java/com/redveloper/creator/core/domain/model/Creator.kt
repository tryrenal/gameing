package com.redveloper.creator.core.domain.model

data class Creator(
    val id: Int,
    val name: String,
    val slug: String?,
    val image: String?,
    val imageBackground: String?,
    val gamesCount: Int?
)