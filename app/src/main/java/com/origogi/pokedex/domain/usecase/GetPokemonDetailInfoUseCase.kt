package com.origogi.pokedex.domain.usecase

import com.origogi.pokedex.data.repository.network.PokedexApiClient
import com.origogi.pokedex.domain.model.PokemonDetailInfo
import com.origogi.pokedex.domain.model.PokemonInfo
import com.origogi.pokedex.domain.model.PokemonType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetPokemonDetailInfoUseCase @Inject constructor(
    private val pokedexApiClient: PokedexApiClient
){
    suspend fun execute(id: Int) : Flow<PokemonDetailInfo> = flow {

        emit(
            PokemonDetailInfo(
                pokedexId = 6,
                name = "Bulbasaur",
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/6.gif",
                types = listOf(
                    PokemonType.Fire,
                    PokemonType.Water
                ),
                height = 7.0,
                weight = 69.0,
                desc = "It is a small quadruped Pokémon that has blue fur with a white face and paws. It has rounded ears with pink insides, big blue eyes, and a small black nose. Its paws each have three toes, with the outer two being larger than the inner one. It also has a long, curled tail."
            )
        )
    }
}