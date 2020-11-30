package com.mirkopruiti.kotlinpokemontest.di

import com.mirkopruiti.kotlinpokemontest.repository.DetailsRepository
import com.mirkopruiti.kotlinpokemontest.repository.HomeDataSource
import com.mirkopruiti.kotlinpokemontest.repository.HomeRepository
import com.mirkopruiti.kotlinpokemontest.repository.LocalDataSource
import org.koin.dsl.module

val repositoryModule = module {

    single { HomeRepository(get(), get()) }
    single { HomeDataSource(get(), get()) }
    single { LocalDataSource(get()) }

    single { DetailsRepository(get(), get()) }

}