package com.redveloper.home.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.redveloper.home.core.domain.usecase.HomeUseCase
import java.util.*

class HomeViewModel(
    private val useCase: HomeUseCase
) : ViewModel() {
    private val calender = Calendar.getInstance()

    fun games() = useCase.getAllGame().asLiveData()
    fun setGreeting(): String {
        return when (calender.get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> "Good Morning"
            in 12..15 -> "Good Afternoon"
            in 16..20 -> "Good Evening"
            in 21..23 -> "Good Night"
            else -> ""
        }
    }

    fun getFavoriteGame() = useCase.getFavoriteGame().asLiveData()
}