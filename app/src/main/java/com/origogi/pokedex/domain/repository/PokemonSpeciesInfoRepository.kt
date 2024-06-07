package com.origogi.pokedex.domain.repository

import com.origogi.pokedex.domain.model.PokemonSpeciesInfo
import kotlinx.coroutines.flow.Flow

interface PokemonSpeciesInfoRepository {
    fun get(id: Int): Flow<PokemonSpeciesInfo>
}