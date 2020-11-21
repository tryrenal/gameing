package com.redveloper.home.ui.list

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.redveloper.home.core.domain.usecase.HomeUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import java.util.*

class HomeViewModel(
    private val useCase: HomeUseCase
) : ViewModel() {
    private val calender = Calendar.getInstance()
    val query : MutableLiveData<String> = MutableLiveData("")

    @FlowPreview
    @ExperimentalCoroutinesApi
    val game = query.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest {
            getAllGamesPager(it)
        }.cachedIn(viewModelScope)

    private fun getAllGamesPager(search: String? = null) = useCase.getAllGamePager(search)
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