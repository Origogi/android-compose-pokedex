package com.origogi.pokedex.data.repository

import com.origogi.pokedex.data.dto.PokemonData
import com.origogi.pokedex.data.repository.network.PokedexApiClient
import com.origogi.pokedex.domain.model.PokemonInfo
import com.origogi.pokedex.domain.model.PokemonType
import com.origogi.pokedex.domain.repository.PokemonInfoRepository
import com.origogi.pokedex.extenstion.capitalizeFirstChar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonInfoRepositoryImpl @Inject constructor(
    private val pokedexApiClient: PokedexApiClient
) : PokemonInfoRepository {
    override fun getById(id: Int) : Flow<PokemonInfo> = flow {
        val data = pokedexApiClient.fetchPokemonDataById(id.toString())
        emit(mapToPokemonInfo(data))
    }


    override fun getByName(name: String): Flow<PokemonInfo> = flow {
        val data = pokedexApiClient.fetchPokemonDataByName(name.lowercase())
        emit(mapToPokemonInfo(data))
    }

    private fun mapToPokemonInfo(data: PokemonData) : PokemonInfo {
       return PokemonInfo(
            pokedexId = data.id,
            name = data.name.capitalizeFirstChar(),
            imageUrl = data.sprites.frontDefault,
            gifImageUrl = data.sprites.other.showdown.frontDefault,
            types = data.types.map {
                PokemonType.valueOf(it.type.name.capitalizeFirstChar())
            },
            height = data.height,
            weight = data.weight,
            abilities = data.abilities.map { it.ability.name }
        )
    }
}