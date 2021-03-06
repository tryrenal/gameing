package com.redveloper.gameing

import android.app.Application
import com.redveloper.core.di.databaseModule
import com.redveloper.core.di.networkModule
import com.redveloper.gameing.di.repository
import com.redveloper.gameing.di.useCaseModule
import com.redveloper.gameing.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        //koin
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repository,
                    useCaseModule,
                    viewModelModule
                )
            )
        }

        //timber
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}