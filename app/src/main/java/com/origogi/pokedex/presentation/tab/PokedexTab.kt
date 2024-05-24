package com.origogi.pokedex.presentation.tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.origogi.pokedex.domain.PokemonCardInfo
import com.origogi.pokedex.domain.PokemonType
import com.origogi.pokedex.presentation.components.PokemonCard
import com.origogi.pokedex.presentation.theme.PokedexTheme

@Composable
fun PokedexTab(
    modifier: Modifier = Modifier
) {
    Box(modifier.fillMaxSize()) {
        PokemonCard(
            pokemonCardInfo = PokemonCardInfo(
                1,
                "Bulbasaur",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                listOf(PokemonType.Grass, PokemonType.Poison)
            )
        )
    }
}

@Preview(name = "PokedexTab")
@Composable
private fun PreviewPokedexTab() {
    PokedexTheme {
        PokedexTab()

    }
}