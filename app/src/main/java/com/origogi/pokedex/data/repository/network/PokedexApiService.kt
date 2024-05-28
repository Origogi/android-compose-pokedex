package com.origogi.pokedex.data.repository.network

import com.origogi.pokedex.data.dto.PokemonData
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexApiService {

    @GET("pokemon/{id}")
    suspend fun getPokemonData(@Path("id") id: String): PokemonData
}