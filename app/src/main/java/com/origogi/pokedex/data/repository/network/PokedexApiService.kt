package com.origogi.pokedex.data.repository.network

import com.origogi.pokedex.data.dto.PokemonData
import com.origogi.pokedex.data.dto.PokemonEvolutionChainData
import com.origogi.pokedex.data.dto.PokemonSpeciesData
import com.origogi.pokedex.data.dto.PokemonTypeDetailData
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexApiService {

    @GET("pokemon/{id}")
    suspend fun getPokemonDataById(@Path("id") id: String): PokemonData


    @GET("pokemon/{name}")
    suspend fun getPokemonDataByName(@Path("name") id: String): PokemonData

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpeciesData(@Path("id") id: String): PokemonSpeciesData

    @GET("type/{type}")
    suspend fun getPokemonTypeDetailData(@Path("type") type: String): PokemonTypeDetailData

    @GET("evolution-chain/{id}")
    suspend fun getPokemonEvolutionChainData(@Path("id") id: String): PokemonEvolutionChainData

}