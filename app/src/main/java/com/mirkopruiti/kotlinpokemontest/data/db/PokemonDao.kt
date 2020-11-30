package com.mirkopruiti.kotlinpokemontest.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mirkopruiti.kotlinpokemontest.data.model.Pokemon
import com.mirkopruiti.kotlinpokemontest.data.model.PokemonInfo

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonList: List<Pokemon>)

    @Query("SELECT * FROM Pokemon WHERE page = :page_")
    suspend fun getPokemonList(page_: Int): List<Pokemon>


    @Query("SELECT * FROM Pokemon")
    fun getPokemonLists(): PagingSource<Int, Pokemon>

    @Query("DELETE FROM Pokemon")
    suspend fun clearPokemons()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonInfo(pokemonInfo: PokemonInfo)

    @Query("SELECT * FROM PokemonInfo WHERE name = :name_")
    suspend fun getPokemonInfo(name_: String): PokemonInfo

}
