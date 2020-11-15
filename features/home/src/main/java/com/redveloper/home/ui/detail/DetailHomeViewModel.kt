package com.redveloper.home.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.redveloper.home.core.domain.model.Game
import com.redveloper.home.core.domain.usecase.HomeUseCase

class DetailHomeViewModel(
    private val useCase: HomeUseCase
) : ViewModel(){

    fun setFavorite(data : Game, state: Boolean) = useCase.setFavoriteGame(data, state)

    fun getDetailGame(id : Int) = useCase.getGameById(id).asLiveData()
}