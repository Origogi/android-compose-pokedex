package com.origogi.pokedex.domain.repository

import com.origogi.pokedex.domain.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface PokemonInfoRepository {
    fun getById(id: Int): Flow<PokemonInfo>
    fun getByName(name: String): Flow<PokemonInfo>
}