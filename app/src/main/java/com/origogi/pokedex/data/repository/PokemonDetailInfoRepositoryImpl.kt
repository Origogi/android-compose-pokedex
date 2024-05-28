package com.origogi.pokedex.data.repository

import com.origogi.pokedex.data.repository.network.PokedexApiClient
import com.origogi.pokedex.domain.model.PokemonDetailInfo
import com.origogi.pokedex.domain.model.PokemonType
import com.origogi.pokedex.domain.repository.PokemonDetailInfoRepository
import com.origogi.pokedex.extenstion.capitalizeFirstChar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonDetailInfoRepositoryImpl @Inject constructor(
    private val pokedexApiClient: PokedexApiClient
) : PokemonDetailInfoRepository {
    override suspend fun getPokemonDetailInfo(id: Int): Flow<PokemonDetailInfo> = flow {

        val data = withContext(Dispatchers.IO) {
            pokedexApiClient.fetchPokemonData(id.toString())
        }

        val detailInfo = PokemonDetailInfo(
            pokedexId = data.id,
            name = data.name,
            imageUrl = data.sprites.frontDefault,
            types = data.types.map {
                PokemonType.valueOf(it.type.name.capitalizeFirstChar())
            },
            height = 0,
            weight = 0
        )
        emit(detailInfo)
    }.catch { e ->
        e.printStackTrace()

    }

}