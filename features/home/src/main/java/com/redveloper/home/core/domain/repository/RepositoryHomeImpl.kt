package com.redveloper.home.core.domain.repository

import androidx.paging.PagingData
import com.redveloper.core.vo.Resource
import com.redveloper.home.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface RepositoryHomeImpl {
    fun getGameById(id: Int): Flow<Resource<Game>>
    fun getAllGamePager() : Flow<PagingData<Game>>
    fun getFavoriteGame() : Flow<PagingData<Game>>
    fun setFavoriteGame(game: Game, state: Boolean)
}