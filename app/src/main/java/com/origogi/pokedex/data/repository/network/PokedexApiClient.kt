package com.origogi.pokedex.data.repository.network

import javax.inject.Inject

class PokedexApiClient @Inject constructor(
    private val pokedexApiService: PokedexApiService
){
    suspend fun fetchPokemonData(id: String) = pokedexApiService.getPokemonData(id)
    suspend fun fetchPokemonSpeciesData(id: String) = pokedexApiService.getPokemonSpeciesData(id)
}