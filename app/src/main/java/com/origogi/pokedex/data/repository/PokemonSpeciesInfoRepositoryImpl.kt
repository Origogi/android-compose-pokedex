package com.origogi.pokedex.data.repository

import com.origogi.pokedex.data.dto.getEnglishFlavorText
import com.origogi.pokedex.data.dto.getEnglishGenusText
import com.origogi.pokedex.data.repository.network.PokedexApiClient
import com.origogi.pokedex.domain.model.PokemonSpeciesInfo
import com.origogi.pokedex.domain.repository.PokemonSpeciesInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonSpeciesInfoRepositoryImpl @Inject constructor(
    private val pokedexApiClient: PokedexApiClient

) : PokemonSpeciesInfoRepository {
    override suspend fun get(id: Int): Flow<PokemonSpeciesInfo> = flow {
        val speciesData = pokedexApiClient.fetchPokemonSpeciesData(id.toString())
        val desc = speciesData.getEnglishFlavorText().replace("\n", " ")
        val category = parseCategory(speciesData.getEnglishGenusText())

        emit(PokemonSpeciesInfo(
            desc = desc,
            category = category
        ))
    }

    private fun parseCategory(fullString: String): String {
        val regex = """(.*?) Pokémon""".toRegex()
        val matchResult = regex.find(fullString)
        return matchResult?.groups?.get(1)?.value ?: ""
    }
}