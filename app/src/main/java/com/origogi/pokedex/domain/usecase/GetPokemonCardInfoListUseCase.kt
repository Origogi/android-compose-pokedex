package com.origogi.pokedex.domain.usecase

import com.origogi.pokedex.domain.model.PokemonCardInfo
import com.origogi.pokedex.domain.repository.PokemonCardInfoListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonCardInfoListUseCase @Inject constructor(
    private val pokemonCardInfoListRepository: PokemonCardInfoListRepository
) {

    companion object {
        const val LIMIT = 20
    }

    suspend fun execute(page: Int): Flow<List<PokemonCardInfo>> =
        pokemonCardInfoListRepository.list(
            offset = page * LIMIT,
            limit = LIMIT
        )
}