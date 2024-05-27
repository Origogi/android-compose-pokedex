package com.origogi.pokedex.data.repository

import com.origogi.pokedex.domain.model.PokemonCardInfo
import com.origogi.pokedex.domain.model.PokemonType
import com.origogi.pokedex.domain.repository.PokemonCardInfoListRepository
import com.origogi.pokedex.extenstion.sample
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class FakePokemonCardInfoListRepositoryImpl : PokemonCardInfoListRepository {
    override suspend fun list(offset: Int, limit: Int): Flow<List<PokemonCardInfo>> = flow {
        val pokemonCardInfoList = (1..20).map {
            PokemonCardInfo(
                pokedexId = it,
                name = "Pokemon $it",
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$it.png",
                types = PokemonType.values.sample(Random.nextInt(1,2))
            )
        }
        emit(pokemonCardInfoList)
    }
}