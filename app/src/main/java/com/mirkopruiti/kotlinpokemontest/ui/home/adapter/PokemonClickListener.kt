package com.mirkopruiti.kotlinpokemontest.ui.home.adapter

import com.mirkopruiti.kotlinpokemontest.data.model.Pokemon

interface PokemonClickListener {
    fun onPokemonClickListener(poke: Pokemon)
}