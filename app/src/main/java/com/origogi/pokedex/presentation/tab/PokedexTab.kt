package com.origogi.pokedex.presentation.tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.origogi.pokedex.domain.PokemonCardInfo
import com.origogi.pokedex.domain.PokemonType
import com.origogi.pokedex.presentation.components.PokemonCard
import com.origogi.pokedex.presentation.theme.PokedexTheme
import com.origogi.pokedex.presentation.viewmodel.PokedexTabViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PokedexTab(
    modifier: Modifier = Modifier,
    viewModel: PokedexTabViewModel = hiltViewModel()
) {

    val list = viewModel.pokemonCardInfos
    Box(modifier.fillMaxSize()) {
        LazyColumn {
            items(list) { pokemonCardInfo ->
                PokemonCard(pokemonCardInfo)
            }
        }
    }
}

@Preview(name = "PokedexTab")
@Composable
private fun PreviewPokedexTab() {
    PokedexTheme {
        PokedexTab()

    }
}