package com.mirkopruiti.kotlinpokemontest.di

import com.mirkopruiti.kotlinpokemontest.data.api.ApiCall
import com.mirkopruiti.kotlinpokemontest.data.api.ApiService
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {

    single { Moshi.Builder().build() }

    single {  OkHttpClient.Builder().build() }

    single {  Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }

    single { ApiCall(get()) }

}
