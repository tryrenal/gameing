package com.redveloper.home.core.di

import com.redveloper.core.data.source.local.LocalDataSource
import com.redveloper.core.data.source.remote.RemoteDataSource
import com.redveloper.core.utils.AppExecutors
import com.redveloper.home.core.data.RepositoryHome
import com.redveloper.home.core.domain.repository.RepositoryHomeImpl
import org.koin.dsl.module

val repositoryHome = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<RepositoryHomeImpl> { RepositoryHome(get(), get(), get()) }
}