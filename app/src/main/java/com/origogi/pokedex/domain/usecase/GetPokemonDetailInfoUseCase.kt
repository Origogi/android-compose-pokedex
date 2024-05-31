package com.origogi.pokedex.domain.usecase

import com.origogi.pokedex.domain.model.PokemonDetailInfo
import com.origogi.pokedex.domain.repository.PokemonInfoRepository
import com.origogi.pokedex.domain.repository.PokemonSpeciesInfoRepository
import com.origogi.pokedex.domain.repository.PokemonWeaknessTypesRepository
import com.origogi.pokedex.extenstion.capitalizeFirstChar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class GetPokemonDetailInfoUseCase @Inject constructor(
    private val pokemonInfoRepository: PokemonInfoRepository,
    private val pokemonSpeciesInfoRepository: PokemonSpeciesInfoRepository,
    private val pokemonWeaknessTypesRepository: PokemonWeaknessTypesRepository
) {
    suspend fun execute(id: Int): Flow<PokemonDetailInfo> = combine(
        pokemonInfoRepository.get(id),
        pokemonSpeciesInfoRepository.get(id)
    ) { pokemonInfo, pokemonSpeciesInfo ->
        Pair(pokemonInfo, pokemonSpeciesInfo)
    }.flatMapLatest { (info, speciesInfo) ->
        pokemonWeaknessTypesRepository.get(info.types.first()).map { weaknessTypes ->
            Triple(info, speciesInfo, weaknessTypes)
        }
    }.map { (info, speciesInfo, weaknessTypes) ->
        PokemonDetailInfo(
            pokedexId = info.pokedexId,
            name = info.name.capitalizeFirstChar(),
            imageUrl = info.imageUrl,
            types = info.types,
            height = info.height,
            weight = info.weight,
            desc = speciesInfo.desc,
            animatedImageUrl = info.gifImageUrl,
            abilities = info.abilities.map { formatSpeciesName(it) }.toList(),
            category = speciesInfo.category,
            genderRatio = speciesInfo.genderRate,
            weaknessTypes = weaknessTypes
        )

    }

    private fun formatSpeciesName(input: String): String {
        return input.split("-").joinToString(" ") {
            it.capitalizeFirstChar()
        }

    }

}