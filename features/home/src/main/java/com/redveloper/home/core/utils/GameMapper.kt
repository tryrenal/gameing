package com.redveloper.home.core.utils

import androidx.paging.PagingData
import androidx.paging.flatMap
import androidx.paging.map
import com.redveloper.core.data.source.local.entity.GameEntity
import com.redveloper.core.data.source.remote.response.game.GameResponse
import com.redveloper.home.core.domain.model.Game

object GameMapper {
    fun responseToEntity(input: List<GameResponse>) : List<GameEntity> {
        return input.map {
            GameEntity(
                id = it.id,
                name = it.name,
                slug = it.slug,
                released = it.released,
                backgroundImage = it.background_image,
                rating = it.rating,
                rating_top = it.ratingTop
            )
        }
    }

    fun entityToDomain(input: PagingData<GameEntity>) : PagingData<Game>{
        return input.map {
             Game(
                id = it.id,
                name = it.name,
                slug = it.slug,
                released = it.released,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                ratingTop = it.rating_top
            )
        }
    }
}