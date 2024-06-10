package com.origogi.pokedex.data.repository

import com.origogi.pokedex.data.dto.ChainData
import com.origogi.pokedex.data.repository.network.PokedexApiClient
import com.origogi.pokedex.domain.model.PokemonCardInfo
import com.origogi.pokedex.domain.model.PokemonEvolutionChainInfo
import com.origogi.pokedex.domain.model.PokemonType
import com.origogi.pokedex.domain.repository.PokemonEvolutionChainInfoRepository
import com.origogi.pokedex.extenstion.capitalizeFirstChar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)

class PokemonEvolutionChainInfoRepositoryImpl @Inject constructor(
    private val client: PokedexApiClient,
) : PokemonEvolutionChainInfoRepository {
    override fun get(id: Int): Flow<PokemonEvolutionChainInfo?> =
        client.fetchPokemonEvolutionChainData(id.toString()).mapLatest { data ->
            mapInfo(data.chain)
        }

    private suspend fun mapInfo(chainData: ChainData?): PokemonEvolutionChainInfo? {
        return chainData?.let { data ->
            val pokemonDetailData = client.fetchPokemonDataByName(data.species.name)

            val cardInfo = PokemonCardInfo(
                pokedexId = pokemonDetailData.id,
                name = pokemonDetailData.name,
                imageUrl = pokemonDetailData.sprites.versions.generationViii.icons.frontDefault,
                types = pokemonDetailData.types.map { PokemonType.valueOf(it.type.name.capitalizeFirstChar()) }
            )

            val nextChain = mapInfo(data.evolvesTo.firstOrNull())
            PokemonEvolutionChainInfo(
                cardInfo = cardInfo,
                next = nextChain
            )
        }
    }
}