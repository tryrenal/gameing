package com.redveloper.creator.core.utils

import com.redveloper.core.data.source.local.entity.CreatorEntity
import com.redveloper.core.data.source.remote.response.creator.CreatorResponse
import com.redveloper.creator.core.domain.model.Creator

object CreatorMapper  {
    fun responseToEntity(input: List<CreatorResponse>) : List<CreatorEntity>{
        return input.map {
            CreatorEntity(
                id = it.id,
                name = it.name,
                slug = it.slug,
                image = it.image,
                imageBackgroudn = it.imageBackground,
                gamesCount = it.gamesCount
            )
        }
    }

    fun entityToDomain(input: List<CreatorEntity>) : List<Creator>{
        return input.map {
            Creator(
                id = it.id,
                name = it.name,
                slug = it.slug,
                image = it.image,
                imageBackground = it.imageBackgroudn,
                gamesCount = it.gamesCount
            )
        }
    }
}