package com.origogi.pokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.origogi.pokedex.domain.repository.PokemonFavoriteRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = LikeButtonViewModel.LikeButtonViewModelFactory::class)
class LikeButtonViewModel @AssistedInject constructor(
    @Assisted private val pokedexId : Int,
    private val favoriteRepository: PokemonFavoriteRepository
) : ViewModel() {

    @AssistedFactory
    interface LikeButtonViewModelFactory {
        fun create(pokedexId: Int): LikeButtonViewModel
    }


    val isFavorite: StateFlow<Boolean> = favoriteRepository.list().map {
        it.contains(pokedexId)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    fun toggle() {
        println(pokedexId)
        viewModelScope.launch {
            if (isFavorite.value) {
                favoriteRepository.remove(pokedexId)
            } else {
                favoriteRepository.add(pokedexId)
            }
        }
    }
}