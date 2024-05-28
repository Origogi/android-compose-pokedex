package com.origogi.pokedex.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.origogi.pokedex.domain.model.PokemonCardInfo
import com.origogi.pokedex.domain.model.PokemonType
import com.origogi.pokedex.domain.usecase.GetPokemonCardInfoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PokedexTabViewModel @Inject constructor(
    private val useCase: GetPokemonCardInfoListUseCase
) :
    ViewModel() {

    companion object {
        private const val PAGE_SIZE = 20
    }

    private val fetchPage: MutableStateFlow<Int> = MutableStateFlow(0)

    val list: StateFlow<List<PokemonCardInfo>> = fetchPage.flatMapLatest { page ->
        useCase.execute(offset = page * PAGE_SIZE + 1, limit = PAGE_SIZE - 1)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )
}