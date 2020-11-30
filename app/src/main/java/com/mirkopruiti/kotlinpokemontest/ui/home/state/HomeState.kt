package com.mirkopruiti.kotlinpokemontest.ui.home.state

import androidx.paging.PagingData
import com.mirkopruiti.kotlinpokemontest.data.model.Pokemon
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.flow.Flow

sealed class HomeState : UIState(){
    object Init : HomeState()
    data class Pokemons(val pokemon: Flow<PagingData<Pokemon>>) : HomeState()
    data class Error(val error: String?) : HomeState()
}