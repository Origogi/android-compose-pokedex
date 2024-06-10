package com.origogi.pokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.origogi.pokedex.domain.usecase.AddFavoriteUseCase
import com.origogi.pokedex.domain.usecase.RemoveFavoriteUseCase
import com.origogi.pokedex.domain.usecase.WatchIsFavoriteUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = LikeButtonViewModel.LikeButtonViewModelFactory::class)
class LikeButtonViewModel @AssistedInject constructor(
    @Assisted private val pokedexId: Int,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    watchIsFavoriteUseCase: WatchIsFavoriteUseCase,
) : ViewModel() {

    @AssistedFactory
    interface LikeButtonViewModelFactory {
        fun create(pokedexId: Int): LikeButtonViewModel
    }


    val isFavorite: StateFlow<Boolean> = watchIsFavoriteUseCase.execute(pokedexId).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    fun toggle() {
        println(pokedexId)
        viewModelScope.launch {
            if (isFavorite.value) {
                removeFavoriteUseCase.execute(pokedexId)
            } else {
                addFavoriteUseCase.execute(pokedexId)
            }
        }
    }
}