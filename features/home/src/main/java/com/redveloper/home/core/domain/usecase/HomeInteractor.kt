package com.redveloper.home.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.redveloper.core.vo.Resource
import com.redveloper.home.core.domain.model.Game
import com.redveloper.home.core.domain.repository.RepositoryHomeImpl
import kotlinx.coroutines.flow.Flow

class HomeInteractor (
    private val repositoryHomeImpl: RepositoryHomeImpl
) : HomeUseCase {
    override fun getAllGame(): Flow<Resource<PagingData<Game>>> = repositoryHomeImpl.getAllGames()
    override fun getGameById(id: Int): LiveData<Game> = repositoryHomeImpl.getGameById(id)
}