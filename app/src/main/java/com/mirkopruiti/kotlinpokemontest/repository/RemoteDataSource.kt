package com.mirkopruiti.kotlinpokemontest.repository

import androidx.paging.PagingSource
import com.bumptech.glide.load.HttpException
import com.mirkopruiti.kotlinpokemontest.data.api.ApiCall
import com.mirkopruiti.kotlinpokemontest.data.db.PokemonDao
import com.mirkopruiti.kotlinpokemontest.data.model.Pokemon
import java.io.IOException

const val STARTING_PAGE_INDEX = 0

class HomeDataSource(private val apiCall: ApiCall, private val pokemonDao: PokemonDao) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apiCall.fetchPokemonList(page)
            val pokemons = response.body()?.results ?: emptyList()
            for (poke in pokemons) {
                poke.setPokemonId()
                poke.setPokemonPage(page)
            }
            pokemonDao.insertPokemonList(pokemons)
            LoadResult.Page(
                data = pokemons,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (pokemons.isEmpty()) null else page + 1
            )

        } catch (exception: IOException) {
            val error = IOException("Please Check Internet Connection")
            LoadResult.Error(error)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}
