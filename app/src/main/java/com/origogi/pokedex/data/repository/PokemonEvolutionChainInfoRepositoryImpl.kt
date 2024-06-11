package com.origogi.pokedex.data.repository

import com.origogi.pokedex.data.dto.ChainData
import com.origogi.pokedex.data.repository.network.PokedexApiClient
import com.origogi.pokedex.domain.model.PokemonCardInfo
import com.origogi.pokedex.domain.model.PokemonEvolutionChainInfo
import com.origogi.pokedex.domain.model.PokemonType
import com.origogi.pokedex.domain.repository.PokemonEvolutionChainInfoRepository
import com.origogi.pokedex.extenstion.capitalizeFirstChar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class PokemonEvolutionChainInfoRepositoryImpl @Inject constructor(
    private val client: PokedexApiClient,
) : PokemonEvolutionChainInfoRepository {
    override fun get(id: Int): Flow<PokemonEvolutionChainInfo?> = flow {
        val data = client.fetchPokemonEvolutionChainData(id.toString())
        emit(mapInfo(data.chain))
    }.flowOn(Dispatchers.IO)

    private suspend fun mapInfo(chainData: ChainData?): PokemonEvolutionChainInfo? {
        return chainData?.let { data ->
            val cardInfo = getCardInfo(data.species.name)

            val nextChain = mapInfo(data.evolvesTo.firstOrNull())
            PokemonEvolutionChainInfo(
                cardInfo = cardInfo,
                next = nextChain
            )
        }
    }

    private suspend fun getCardInfo(name : String) : PokemonCardInfo {
        val pokemonDetailData = client.fetchPokemonDataByName(name)

        return PokemonCardInfo(
            pokedexId = pokemonDetailData.id,
            name = pokemonDetailData.name,
            imageUrl = pokemonDetailData.sprites.versions.generationVii.icons.frontDefault,
            types = pokemonDetailData.types.map { PokemonType.valueOf(it.type.name.capitalizeFirstChar()) }
        )
    }
}