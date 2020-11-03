package com.redveloper.home.ui.detail

import androidx.lifecycle.ViewModel
import com.redveloper.home.core.domain.usecase.HomeUseCase

class DetailHomeViewModel(
    private val useCase: HomeUseCase
) : ViewModel(){

    fun getDetailGame(id : Int) = useCase.getGameById(id)
}