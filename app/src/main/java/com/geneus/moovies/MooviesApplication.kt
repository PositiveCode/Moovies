package com.geneus.moovies

import android.app.Application
import com.geneus.moovies.di.module.appModule
import com.geneus.moovies.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MooviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MooviesApplication)
            modules(listOf(appModule, viewModelModule))
        }
    }
}