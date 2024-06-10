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
    override fun get(id: Int): Flow<PokemonSpeciesInfo> = flow {
        val speciesData = pokedexApiClient.fetchPokemonSpeciesData(id.toString())
        val desc = speciesData.getEnglishFlavorText().replace("\n", " ")
        val category = parseCategory(speciesData.getEnglishGenusText())
        val genderRate = speciesData.genderRate.let { ratio ->
            when (ratio) {
                -1 -> null
                else -> (8 - ratio.toDouble()) / 8
            }
        }

        val evolutionChainId = parseEvolutionId(speciesData.evolutionChain.url)

        emit(PokemonSpeciesInfo(
            desc = desc,
            category = category,
            genderRate = genderRate,
            evolutionChainId = evolutionChainId
        ))
    }

    private fun parseCategory(fullString: String): String {
        val regex = """(.*?) Pokémon""".toRegex()
        val matchResult = regex.find(fullString)
        return matchResult?.groups?.get(1)?.value ?: ""
    }

    private fun parseEvolutionId(url : String) : Int {
        val regex = """/(\d+)/$""".toRegex()
        val matchResult = regex.find(url)
        return matchResult?.groups?.get(1)?.value?.toInt() ?: 0
    }
}