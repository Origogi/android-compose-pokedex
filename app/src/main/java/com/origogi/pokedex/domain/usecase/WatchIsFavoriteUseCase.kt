package com.origogi.pokedex.domain.usecase

import com.origogi.pokedex.domain.repository.PokemonFavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WatchIsFavoriteUseCase @Inject constructor(
    private val repository: PokemonFavoriteRepository
) {
    fun execute(pokedexId: Int) : Flow<Boolean> =  repository.list().map {
        it.contains(pokedexId)
    }
}