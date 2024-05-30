package com.origogi.pokedex.data.repository

import com.origogi.pokedex.data.dto.getEnglishFlavorText
import com.origogi.pokedex.data.repository.network.PokedexApiClient
import com.origogi.pokedex.domain.repository.PokemonSpeciesInfoRepository
import com.origogi.pokedex.domain.model.PokemonSpeciesInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonSpeciesInfoRepositoryImpl @Inject constructor(
    private val pokedexApiClient: PokedexApiClient

) : PokemonSpeciesInfoRepository {
    override suspend fun get(id: Int): Flow<PokemonSpeciesInfo> = flow {
        val speciesData = pokedexApiClient.fetchPokemonSpeciesData(id.toString())
        val desc = speciesData.getEnglishFlavorText().replace("\n", " ")

        emit(PokemonSpeciesInfo(desc))
    }
}