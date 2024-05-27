package com.origogi.pokedex.domain.repository

import com.origogi.pokedex.domain.model.PokemonCardInfo
import kotlinx.coroutines.flow.Flow

interface PokemonCardInfoListRepository {

    suspend fun list(offset: Int, limit: Int = 20): Flow<List<PokemonCardInfo>>
}