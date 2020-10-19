package com.redveloper.gameing

import android.app.Application
import com.redveloper.core.di.databaseModule
import com.redveloper.core.di.networkModule
import com.redveloper.home.di.repositoryHome
import com.redveloper.home.di.useCaseModule
import com.redveloper.home.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryHome,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}