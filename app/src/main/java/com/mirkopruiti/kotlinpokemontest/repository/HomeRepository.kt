package com.mirkopruiti.kotlinpokemontest.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mirkopruiti.kotlinpokemontest.data.model.Pokemon
import kotlinx.coroutines.flow.Flow

class HomeRepository (private val homeDataSource: HomeDataSource, private val localDataSource: LocalDataSource) {

    fun getRemotePokemonList(): Flow<PagingData<Pokemon>> {
            return Pager(
                config = PagingConfig(pageSize = PAGE_SIZE),
                pagingSourceFactory = { homeDataSource }
            ).flow
    }

    fun getLocalPokemonList(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { localDataSource }
        ).flow
    }

    companion object {
        const val PAGE_SIZE = 20
    }

}