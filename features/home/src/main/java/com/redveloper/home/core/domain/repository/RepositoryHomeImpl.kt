package com.redveloper.home.core.domain.repository

import androidx.paging.PagingData
import com.redveloper.core.vo.Resource
import com.redveloper.home.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface RepositoryHomeImpl {
    fun getAllGames(): Flow<Resource<PagingData<Game>>>
    fun getGameById(id: Int): Flow<Game>
}