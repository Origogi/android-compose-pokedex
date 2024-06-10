package com.origogi.pokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.origogi.pokedex.domain.usecase.RemoveFavoriteUseCase
import com.origogi.pokedex.domain.usecase.WatchFavoritePokemonCardListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteTabViewModel @Inject constructor(
    watchFavoritePokemonCardListUseCase: WatchFavoritePokemonCardListUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
): ViewModel() {

    val list = watchFavoritePokemonCardListUseCase.execute().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun removeFavorite(pokedexId: Int) {
        viewModelScope.launch {
            removeFavoriteUseCase.execute(pokedexId)
        }
    }
}