package com.redveloper.home.core.domain.usecase

import androidx.paging.PagingData
import com.redveloper.core.vo.Resource
import com.redveloper.home.core.domain.model.Game
import com.redveloper.home.core.domain.repository.RepositoryHomeImpl
import kotlinx.coroutines.flow.Flow

class HomeInteractor(
    private val repositoryHomeImpl: RepositoryHomeImpl
) : HomeUseCase {
    override fun getAllGamePager(): Flow<PagingData<Game>> = repositoryHomeImpl.getAllGamePager()
    override fun getGameById(id: Int): Flow<Resource<Game>> = repositoryHomeImpl.getGameById(id)
    override fun setFavoriteGame(data: Game, state: Boolean) = repositoryHomeImpl.setFavoriteGame(data, state)
    override fun getFavoriteGame(): Flow<PagingData<Game>> = repositoryHomeImpl.getFavoriteGame()
}