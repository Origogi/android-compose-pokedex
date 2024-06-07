package com.origogi.pokedex.domain.repository

import com.origogi.pokedex.domain.model.PokemonType
import kotlinx.coroutines.flow.Flow

interface PokemonWeaknessTypesRepository {
    fun get(type: PokemonType): Flow<List<PokemonType>>
}