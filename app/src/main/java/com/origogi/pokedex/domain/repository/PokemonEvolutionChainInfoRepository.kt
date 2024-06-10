package com.origogi.pokedex.domain.repository

import com.origogi.pokedex.domain.model.PokemonEvolutionChainInfo
import kotlinx.coroutines.flow.Flow

interface PokemonEvolutionChainInfoRepository {
    fun get(id: Int): Flow<PokemonEvolutionChainInfo?>
}