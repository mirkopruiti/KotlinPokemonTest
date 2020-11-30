package com.mirkopruiti.kotlinpokemontest.repository

import androidx.paging.PagingSource
import com.bumptech.glide.load.HttpException
import com.mirkopruiti.kotlinpokemontest.data.db.PokemonDao
import com.mirkopruiti.kotlinpokemontest.data.model.Pokemon
import java.io.IOException

const val P_STARTING_PAGE_INDEX = 0

class LocalDataSource(private val pokemonDao: PokemonDao) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val page = params.key ?: P_STARTING_PAGE_INDEX
        return try {
            val pokemons = pokemonDao.getPokemonList(page)
            pokemonDao.insertPokemonList(pokemons)
            LoadResult.Page(
                data = pokemons,
                prevKey = if (page == P_STARTING_PAGE_INDEX) null else page - 1,
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
