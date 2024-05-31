package com.origogi.pokedex.domain.usecase

import com.origogi.pokedex.domain.model.PokemonDetailInfo
import com.origogi.pokedex.domain.repository.PokemonInfoRepository
import com.origogi.pokedex.domain.repository.PokemonSpeciesInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetPokemonDetailInfoUseCase @Inject constructor(
    private val pokemonInfoRepository: PokemonInfoRepository,
    private val pokemonSpeciesInfoRepository: PokemonSpeciesInfoRepository
) {
    suspend fun execute(id: Int): Flow<PokemonDetailInfo> = combine(
        pokemonInfoRepository.get(id),
        pokemonSpeciesInfoRepository.get(id)
    ) { pokemonInfo, pokemonSpeciesInfo ->
        PokemonDetailInfo(
            pokedexId = pokemonInfo.pokedexId,
            name = pokemonInfo.name,
            imageUrl = pokemonInfo.imageUrl,
            types = pokemonInfo.types,
            height = pokemonInfo.height,
            weight = pokemonInfo.weight,
            desc = pokemonSpeciesInfo.desc,
            animatedImageUrl = pokemonInfo.gifImageUrl,
            abilities = listOf("test", "test2"),
            category = "test"
        )
    }

}