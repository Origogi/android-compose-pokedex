package com.origogi.pokedex.domain.usecase

import com.origogi.pokedex.domain.model.PokemonCardInfo
import com.origogi.pokedex.domain.repository.PokemonFavoriteRepository
import com.origogi.pokedex.domain.repository.PokemonInfoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class WatchFavoritePokemonCardListUseCase @Inject constructor(
    private val favoriteRepository: PokemonFavoriteRepository,
    private val pokemonInfoRepository: PokemonInfoRepository
) {

    fun execute(): Flow<List<PokemonCardInfo>> =
        favoriteRepository.list().flatMapLatest { favoriteList ->
            flow {
                val pokemonCardInfoList = favoriteList.asFlow()
                    .flatMapMerge { pokedexId ->
                        pokemonInfoRepository.getById(pokedexId)
                    }
                    .map { detailInfo ->
                        PokemonCardInfo(
                            pokedexId = detailInfo.pokedexId,
                            name = detailInfo.name,
                            imageUrl = detailInfo.imageUrl,
                            types = detailInfo.types
                        )
                    }
                    .toList()
                    .let { list ->
                        list.sortedBy {
                            it.pokedexId
                        }
                    }
                emit(pokemonCardInfoList)
            }
        }

}