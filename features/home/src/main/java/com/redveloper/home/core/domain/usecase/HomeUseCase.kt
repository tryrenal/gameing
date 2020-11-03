package com.redveloper.home.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.redveloper.core.vo.Resource
import com.redveloper.home.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    fun getAllGame() : Flow<Resource<PagingData<Game>>>
    fun getGameById(id: Int): LiveData<Game>
}