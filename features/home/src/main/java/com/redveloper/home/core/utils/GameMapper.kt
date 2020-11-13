package com.redveloper.home.core.utils

import androidx.paging.PagingData
import androidx.paging.map
import com.redveloper.core.data.source.local.entity.GameEntity
import com.redveloper.core.data.source.remote.response.game.GameResponse
import com.redveloper.home.core.domain.model.Game

object GameMapper {
    fun responseToEntityList(input: List<GameResponse>): List<GameEntity> {
        return input.map {
            GameEntity(
                id = it.id,
                name = it.name,
                slug = it.slug,
                released = it.released,
                backgroundImage = it.background_image,
                rating = it.rating,
                rating_top = it.ratingTop,
                description = it.description
            )
        }
    }

    fun responseToEntity(input: GameResponse): GameEntity {
        return GameEntity(
            id = input.id,
            name = input.name,
            slug = input.slug,
            released = input.released,
            backgroundImage = input.background_image,
            rating = input.rating,
            rating_top = input.ratingTop,
            description = input.description
        )
    }
    
    fun entityToDomainPaging(input: PagingData<GameEntity>): PagingData<Game> {
        return input.map {
            Game(
                id = it.id,
                name = it.name,
                slug = it.slug,
                released = it.released,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                ratingTop = it.rating_top,
                desc = it.description
            )
        }
    }

    fun entityToDomain(input: GameEntity): Game {
        return Game(
            id = input.id,
            name = input.name,
            slug = input.slug,
            released = input.released,
            backgroundImage = input.backgroundImage,
            rating = input.rating,
            ratingTop = input.rating_top,
            desc = input.description
        )
    }
}