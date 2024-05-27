package com.origogi.pokedex.presentation.tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.origogi.pokedex.presentation.components.PokemonCard
import com.origogi.pokedex.presentation.theme.PokedexTheme
import com.origogi.pokedex.presentation.viewmodel.PokedexTabViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.origogi.pokedex.data.repository.FakePokemonCardInfoListRepositoryImpl
import com.origogi.pokedex.domain.model.PokemonCardInfo
import com.origogi.pokedex.domain.model.PokemonType
import com.origogi.pokedex.domain.usecase.GetPokemonCardInfoListUseCase

@Composable
fun PokedexTab(
    modifier: Modifier = Modifier,
    viewModel: PokedexTabViewModel = hiltViewModel()
) {

    val list = viewModel.list.collectAsState(initial = emptyList()).value

    Body(modifier = modifier, pokemonCardInfoList = list)

}

@Composable
private fun Body(
    modifier: Modifier = Modifier, pokemonCardInfoList: List<PokemonCardInfo>
) {
    Box(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
        ) {
            items(pokemonCardInfoList) { pokemonCardInfo ->
                Box(Modifier.padding(vertical = 8.dp)) {
                    PokemonCard(pokemonCardInfo)
                }

            }
        }
    }
}

@Preview(name = "PokedexTab", showBackground = true)
@Composable
private fun PreviewPokedexBody() {
    PokedexTheme {
        Body(
            pokemonCardInfoList = listOf(
                PokemonCardInfo(
                    pokedexId = 1,
                    name = "Bulbasaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                    types = listOf(PokemonType.Bug, PokemonType.Dark),
                ),
                PokemonCardInfo(
                    pokedexId = 12,
                    name = "Bulbasaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                    types = listOf(PokemonType.Ice, PokemonType.Dark),
                ),

                )
        )

    }
}