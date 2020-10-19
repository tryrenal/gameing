package com.redveloper.home.core.domain.model

data class Game(
    val id: Int,
    val name: String?,
    val slug: String?,
    val released: String?,
    val backgroundImage: String?,
    val rating: Double?,
    val ratingTop: Int?
)