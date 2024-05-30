package com.origogi.pokedex.data.repository

import com.origogi.pokedex.data.dto.getEnglishFlavorText
import com.origogi.pokedex.data.repository.network.PokedexApiClient
import com.origogi.pokedex.domain.model.PokemonInfo
import com.origogi.pokedex.domain.model.PokemonType
import com.origogi.pokedex.domain.repository.PokemonInfoRepository
import com.origogi.pokedex.extenstion.capitalizeFirstChar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonInfoRepositoryImpl @Inject constructor(
    private val pokedexApiClient: PokedexApiClient
) : PokemonInfoRepository {
    override suspend fun get(id: Int): Flow<PokemonInfo> = flow {

        val data = pokedexApiClient.fetchPokemonData(id.toString())
        val info = PokemonInfo(
            pokedexId = data.id,
            name = data.name,
            imageUrl = data.sprites.frontDefault,
            types = data.types.map {
                PokemonType.valueOf(it.type.name.capitalizeFirstChar())
            },
            height = data.height,
            weight = data.weight,
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