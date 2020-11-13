package com.redveloper.home.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game(
    val id: Int,
    val name: String?,
    val desc: String?,
    val slug: String?,
    val released: String?,
    val backgroundImage: String?,
    val rating: Double?,
    val ratingTop: Int?
) : Parcelable