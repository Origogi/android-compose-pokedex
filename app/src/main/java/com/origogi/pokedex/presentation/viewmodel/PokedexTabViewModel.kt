package com.origogi.pokedex.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.origogi.pokedex.domain.PokemonCardInfo
import com.origogi.pokedex.domain.PokemonType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokedexTabViewModel @Inject constructor() : ViewModel() {
    var pokemonCardInfos by mutableStateOf<List<PokemonCardInfo>>(emptyList())

    init {
        pokemonCardInfos = pokemonCardInfos + PokemonCardInfo(
            pokedexId = 1,
            name = "Bulbasaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            types = listOf(PokemonType.Grass, PokemonType.Poison)
        )

        pokemonCardInfos = pokemonCardInfos + PokemonCardInfo(
            pokedexId = 2,
            name = "Ivysaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
            types = listOf(PokemonType.Grass, PokemonType.Poison)
        )
    }
}