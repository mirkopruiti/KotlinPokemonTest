package com.mirkopruiti.kotlinpokemontest.di

import com.mirkopruiti.kotlinpokemontest.data.db.PokemonDatabase
import org.koin.dsl.module

val databaseModule = module {

    single { PokemonDatabase.getInstance(get()) }
    single { get<PokemonDatabase>().pokemonDao() }

}