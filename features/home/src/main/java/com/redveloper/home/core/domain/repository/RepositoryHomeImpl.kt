package com.redveloper.home.core.domain.repository

import com.redveloper.core.vo.Resource
import com.redveloper.home.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface RepositoryHomeImpl  {
    fun getAllGames() : Flow<Resource<List<Game>>>
}