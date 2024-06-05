package com.origogi.pokedex.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.origogi.pokedex.domain.model.PokemonCardInfo
import com.origogi.pokedex.domain.model.RegionType
import com.origogi.pokedex.domain.usecase.GetPokemonCardInfoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ViewModelState {
    Loading,
    Idle,
}

@HiltViewModel
class PokemonCardListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCase: GetPokemonCardInfoListUseCase
) :
    ViewModel() {

    var list by mutableStateOf<List<PokemonCardInfo>>(emptyList())
        private set

    var needLoadMore by mutableStateOf(true)
        private set

    private val limit = 20
    private var offset = savedStateHandle.get<String>("region").let {
        println("Region : $it")
        if (it != null) {
            RegionType.valueOf(it).pokedexIdRange.first
        } else {
            1
        }
    }
    private var state = ViewModelState.Idle
    private val endPokedexId =
        savedStateHandle.get<String>("region").let {
            if (it != null) {
                RegionType.valueOf(it).pokedexIdRange.last
            } else {
                RegionType.ALOLA.pokedexIdRange.last
            }
        }


    fun loadMore() {
        if (state == ViewModelState.Idle && needLoadMore) {
            viewModelScope.launch {
                state = ViewModelState.Loading

                useCase.execute(offset, limit).map {
                    it.filter { pokemonCardInfo ->
                        pokemonCardInfo.pokedexId <= endPokedexId}
                }.collect {
                    list = list + it
                    offset += it.size

                    if (list.last().pokedexId == endPokedexId) {
                        needLoadMore = false
                    }

                    state = ViewModelState.Idle
                }
            }
        }
    }
}