package com.origogi.pokedex.data.repository

import com.origogi.pokedex.data.repository.network.PokedexApiClient
import com.origogi.pokedex.domain.model.PokemonInfo
import com.origogi.pokedex.domain.model.PokemonType
import com.origogi.pokedex.domain.repository.PokemonInfoRepository
import com.origogi.pokedex.extenstion.capitalizeFirstChar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonInfoRepositoryImpl @Inject constructor(
    private val pokedexApiClient: PokedexApiClient
) : PokemonInfoRepository {
    override fun get(id: Int) = flow {

        val data = pokedexApiClient.fetchPokemonData(id.toString())
        val info = PokemonInfo(
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
        emit(info)
    }
        .catch { e ->
            e.printStackTrace()
        }
        .flowOn(
            Dispatchers.IO
        )
}