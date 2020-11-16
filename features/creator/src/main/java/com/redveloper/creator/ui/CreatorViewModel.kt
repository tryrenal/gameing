package com.redveloper.creator.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.redveloper.creator.core.domain.usecase.CreatorUseCase

class CreatorViewModel (private val useCase: CreatorUseCase) : ViewModel(){
    fun getAllCreator() = useCase.getAllCreator().asLiveData()
    fun getAllCreatorPager() = useCase.getAllCreatorPager().cachedIn(viewModelScope)
}