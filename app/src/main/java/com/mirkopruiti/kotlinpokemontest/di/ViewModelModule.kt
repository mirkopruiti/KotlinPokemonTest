package com.mirkopruiti.kotlinpokemontest.di

import com.mirkopruiti.kotlinpokemontest.ui.details.DetailsViewModel
import com.mirkopruiti.kotlinpokemontest.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel(get()) }

}