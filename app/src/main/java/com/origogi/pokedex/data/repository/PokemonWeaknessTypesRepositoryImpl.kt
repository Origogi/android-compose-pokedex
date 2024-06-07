package com.origogi.pokedex.data.repository

import com.origogi.pokedex.data.repository.network.PokedexApiClient
import com.origogi.pokedex.domain.model.PokemonType
import com.origogi.pokedex.domain.repository.PokemonWeaknessTypesRepository
import com.origogi.pokedex.extenstion.capitalizeFirstChar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonWeaknessTypesRepositoryImpl @Inject constructor(
    private val client: PokedexApiClient
) : PokemonWeaknessTypesRepository {

    private val cache = mutableMapOf<PokemonType, List<PokemonType>>()
    override fun get(type: PokemonType): Flow<List<PokemonType>> = flow {
        if (!cache.containsKey(type)) {

            val data = client.fetchPokemonTypeDetailData(type.name.lowercase())
            val weaknessTypes = data.damageRelations.doubleDamageFrom.map { it.name }.map {
                PokemonType.valueOf(it.capitalizeFirstChar())
            }
            cache[type] = weaknessTypes
        }
        emit(cache[type]!!)

    }.flowOn(
        Dispatchers.IO
    )
}