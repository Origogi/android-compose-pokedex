package com.origogi.pokedex.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.origogi.pokedex.domain.model.PokemonCardInfo
import com.origogi.pokedex.domain.usecase.GetPokemonCardInfoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ViewModelState {
    Loading,
    Idle,
}

@HiltViewModel
class PokedexTabViewModel @Inject constructor(
    private val useCase: GetPokemonCardInfoListUseCase
) :
    ViewModel() {

    private var fetchPage = 1
    private val state = MutableStateFlow(ViewModelState.Idle)

    var list by mutableStateOf<List<PokemonCardInfo>>(emptyList())
        private set

    init {
        loadMore()
    }

    fun loadMore() {
        if (state.value == ViewModelState.Idle) {
            viewModelScope.launch {
                state.value = ViewModelState.Loading
                useCase.execute(fetchPage).collect {
                    list = list + it
                    fetchPage++
                    state.value = ViewModelState.Idle
                }
            }
        }
    }
}