package com.redveloper.gameing.di

import com.redveloper.core.data.source.local.LocalDataSource
import com.redveloper.core.data.source.remote.RemoteDataSource
import com.redveloper.core.utils.AppExecutors
import com.redveloper.home.core.data.RepositoryHome
import com.redveloper.home.core.domain.repository.RepositoryHomeImpl
import com.redveloper.home.core.domain.usecase.HomeInteractor
import com.redveloper.home.core.domain.usecase.HomeUseCase
import com.redveloper.home.ui.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repository = module {
    single { LocalDataSource(get(), get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<RepositoryHomeImpl> { RepositoryHome(get(), get(), get()) }
}

val useCaseModule = module {
    factory<HomeUseCase> { HomeInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}