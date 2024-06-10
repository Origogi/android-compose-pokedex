package com.origogi.pokedex.data.repository.network

import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokedexApiClient @Inject constructor(
    private val pokedexApiService: PokedexApiService
) {
    suspend fun fetchPokemonDataById(id: String) = pokedexApiService.getPokemonDataById(id)

    suspend fun fetchPokemonDataByName(name: String) = pokedexApiService.getPokemonDataByName(name)

    suspend fun fetchPokemonSpeciesData(id: String) = pokedexApiService.getPokemonSpeciesData(id)
    suspend fun fetchPokemonTypeDetailData(type: String) =
        pokedexApiService.getPokemonTypeDetailData(type)

    fun fetchPokemonEvolutionChainData(id: String) = flow {
        emit(pokedexApiService.getPokemonEvolutionChainData(id))
    }
}