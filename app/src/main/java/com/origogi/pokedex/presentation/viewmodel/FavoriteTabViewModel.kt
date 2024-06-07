package com.origogi.pokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.origogi.pokedex.domain.usecase.WatchFavoritePokemonCardListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoriteTabViewModel @Inject constructor(
    private val watchFavoritePokemonCardListUseCase: WatchFavoritePokemonCardListUseCase
): ViewModel() {

    val list = watchFavoritePokemonCardListUseCase.execute().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )
}