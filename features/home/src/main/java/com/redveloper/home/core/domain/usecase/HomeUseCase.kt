package com.redveloper.home.core.domain.usecase

import com.redveloper.core.vo.Resource
import com.redveloper.home.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    fun getAllGame() : Flow<Resource<List<Game>>>
}