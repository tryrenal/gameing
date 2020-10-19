package com.redveloper.home.ui

import androidx.lifecycle.ViewModel
import com.redveloper.home.core.domain.usecase.HomeUseCase

class HomeViewModel (
    private val useCase: HomeUseCase
) : ViewModel() {

    fun games() = useCase.getAllGame()
}