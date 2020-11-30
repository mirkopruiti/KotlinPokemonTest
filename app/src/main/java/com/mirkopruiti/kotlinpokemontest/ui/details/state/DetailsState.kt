package com.mirkopruiti.kotlinpokemontest.ui.details.state

import androidx.lifecycle.LiveData
import com.mirkopruiti.kotlinpokemontest.data.model.PokemonInfo
import io.uniflow.core.flow.data.UIState

sealed class DetailsState : UIState(){
    object Init : DetailsState()
    data class PokeInfo(val pokemonInfo: LiveData<PokemonInfo>) : DetailsState()
    data class Error(val error: String?) : DetailsState()
}