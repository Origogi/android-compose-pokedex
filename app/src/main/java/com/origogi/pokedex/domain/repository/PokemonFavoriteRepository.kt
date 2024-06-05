package com.origogi.pokedex.domain.repository

import kotlinx.coroutines.flow.Flow

interface PokemonFavoriteRepository {
    suspend fun add(pokedexId : Int)
    suspend fun remove(pokedexId : Int)
    suspend fun list() : Flow<List<Int>>
}