package com.origogi.pokedex.data.repository.network

import com.origogi.pokedex.data.dto.PokemonData
import com.origogi.pokedex.data.dto.PokemonSpeciesData
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexApiService {

    @GET("pokemon/{id}")
    suspend fun getPokemonData(@Path("id") id: String): PokemonData

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpeciesData(@Path("id") id: String): PokemonSpeciesData

}