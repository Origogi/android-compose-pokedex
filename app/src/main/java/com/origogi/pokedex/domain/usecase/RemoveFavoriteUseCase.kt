package com.origogi.pokedex.domain.usecase

import com.origogi.pokedex.domain.repository.PokemonFavoriteRepository
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    private val repository: PokemonFavoriteRepository
){
    suspend fun execute(pokedexId: Int) {
        repository.remove(pokedexId)
    }
}