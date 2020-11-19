package com.redveloper.home.core.domain.usecase

import androidx.paging.PagingData
import com.redveloper.core.vo.Resource
import com.redveloper.home.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    fun getAllGamePager() : Flow<PagingData<Game>>
    fun getGameById(id: Int): Flow<Resource<Game>>
    fun setFavoriteGame(data: Game, state: Boolean)
    fun getFavoriteGame() : Flow<PagingData<Game>>
}