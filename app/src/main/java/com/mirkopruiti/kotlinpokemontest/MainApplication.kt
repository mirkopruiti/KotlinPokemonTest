package com.mirkopruiti.kotlinpokemontest

import android.app.Application
import com.mirkopruiti.kotlinpokemontest.di.databaseModule
import com.mirkopruiti.kotlinpokemontest.di.networkModule
import com.mirkopruiti.kotlinpokemontest.di.repositoryModule
import com.mirkopruiti.kotlinpokemontest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(networkModule)
            modules(databaseModule)
            modules(repositoryModule)
            modules(viewModelModule)
        }
    }
}