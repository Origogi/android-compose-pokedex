package com.origogi.pokedex.domain.repository

import com.origogi.pokedex.domain.model.PokemonDetailInfo
import kotlinx.coroutines.flow.Flow

interface PokemonDetailInfoRepository {
    suspend fun getPokemonDetailInfo(id: Int): Flow<PokemonDetailInfo>
}