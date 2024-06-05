package com.origogi.pokedex.domain.usecase

import com.origogi.pokedex.domain.model.PokemonCardInfo
import com.origogi.pokedex.domain.repository.PokemonInfoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class GetPokemonCardInfoListUseCase @Inject constructor(
    private val repository: PokemonInfoRepository
) {


    suspend fun execute(offset : Int, limit : Int): Flow<List<PokemonCardInfo>> =
        flow {
            val list = mutableListOf<PokemonCardInfo>()

            (offset until offset + limit).asFlow()
                .flatMapMerge { pokedexId ->
                    repository.get(pokedexId)
                }
                .collect { detailInfo ->
                    list.add(
                        PokemonCardInfo(
                            pokedexId = detailInfo.pokedexId,
                            name = detailInfo.name,
                            imageUrl = detailInfo.imageUrl,
                            types = detailInfo.types
                        )
                    )
                }

            list.sortBy {
                it.pokedexId
            }
            emit(list)
        }
}