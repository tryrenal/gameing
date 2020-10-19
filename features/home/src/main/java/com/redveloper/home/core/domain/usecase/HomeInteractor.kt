package com.redveloper.home.core.domain.usecase

import com.redveloper.core.vo.Resource
import com.redveloper.home.core.domain.model.Game
import com.redveloper.home.core.domain.repository.RepositoryHomeImpl
import kotlinx.coroutines.flow.Flow

class HomeInteractor (
    private val repositoryHomeImpl: RepositoryHomeImpl
) : HomeUseCase {
    override fun getAllGame(): Flow<Resource<List<Game>>> = repositoryHomeImpl.getAllGames()
}