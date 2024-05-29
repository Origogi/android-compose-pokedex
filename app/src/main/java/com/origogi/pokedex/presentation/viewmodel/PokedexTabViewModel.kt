package com.origogi.pokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.origogi.pokedex.domain.model.PokemonCardInfo
import com.origogi.pokedex.domain.usecase.GetPokemonCardInfoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

enum class ViewModelState {
    Loading,
    Idle,
}

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PokedexTabViewModel @Inject constructor(
    private val useCase: GetPokemonCardInfoListUseCase
) :
    ViewModel() {

    private val fetchPage: MutableStateFlow<Int> = MutableStateFlow(1)

    val state = MutableStateFlow(ViewModelState.Idle)

    // list는 누적된 값을 유지함
    val list: StateFlow<List<PokemonCardInfo>> = fetchPage
        .onEach {
            state.emit(ViewModelState.Loading)
        }
        .flatMapLatest { page ->
            useCase.execute(page)
        }
        .scan(emptyList<PokemonCardInfo>()) { acc, list ->
            acc + list
        }.onEach {
            state.emit(ViewModelState.Idle)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList(),
        )

    fun loadMore() {
        if (state.value == ViewModelState.Idle) {
            fetchPage.value += 1
        }
    }
}