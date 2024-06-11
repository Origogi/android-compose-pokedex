package com.origogi.pokedex.data.repository.network

import javax.inject.Inject

class PokedexApiClient @Inject constructor(
    private val pokedexApiService: PokedexApiService
) {
    suspend fun fetchPokemonDataById(id: String) = pokedexApiService.getPokemonDataById(id)

    suspend fun fetchPokemonDataByName(name: String) = pokedexApiService.getPokemonDataByName(name)

    suspend fun fetchPokemonSpeciesData(id: String) = pokedexApiService.getPokemonSpeciesData(id)
    suspend fun fetchPokemonTypeDetailData(type: String) =
        pokedexApiService.getPokemonTypeDetailData(type)

    suspend fun fetchPokemonEvolutionChainData(id: String) =
        pokedexApiService.getPokemonEvolutionChainData(id)
}