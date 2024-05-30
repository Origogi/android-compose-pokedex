package com.origogi.pokedex.domain.repository

import com.origogi.pokedex.domain.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface PokemonInfoRepository {
    suspend fun get(id: Int): Flow<PokemonInfo>
}