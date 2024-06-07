package com.origogi.pokedex.data.repository

import com.origogi.pokedex.data.repository.network.PokedexApiClient
import com.origogi.pokedex.domain.model.PokemonType
import com.origogi.pokedex.domain.repository.PokemonWeaknessTypesRepository
import com.origogi.pokedex.extenstion.capitalizeFirstChar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class PokemonWeaknessTypesRepositoryImpl @Inject constructor(
    private val client: PokedexApiClient
) : PokemonWeaknessTypesRepository {

    private val _cache = MutableStateFlow<Map<PokemonType, List<PokemonType>>>(emptyMap())
    private val cache: StateFlow<Map<PokemonType, List<PokemonType>>> = _cache

    override fun get(type: PokemonType): Flow<List<PokemonType>> {
        return cache.map {
            it[type] ?: emptyList()
        }
            // If the cache doesn't have the data, fetch it from the network
            .map { weaknessTypes ->
                weaknessTypes.ifEmpty {
                    val typeData =
                        client.fetchPokemonTypeDetailData(type.name.lowercase())
                    val types = typeData.damageRelations.doubleDamageFrom.map {
                        PokemonType.valueOf(it.name.capitalizeFirstChar())
                    }
                    _cache.update {
                        it + (type to types)
                    }
                    types
                }
            }
            .flowOn(Dispatchers.IO)
    }
}