package com.origogi.pokedex.presentation.tab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.origogi.pokedex.presentation.components.PokemonCardListView
import com.origogi.pokedex.presentation.viewmodel.PokemonCardListViewModel
import com.origogi.pokedex.presentation.viewmodel.ViewModelState

@Composable
fun PokedexTab(
    modifier: Modifier = Modifier,
    viewModel: PokemonCardListViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadMore()
    }

    if (viewModel.list.isEmpty()
        && viewModel.state == ViewModelState.Loading) {

        // Placeholder

    } else {
        PokemonCardListView(
            modifier = modifier,
            pokemonCardInfoList = viewModel.list,
            viewModel.needLoadMore
        ) {
            viewModel.loadMore()
        }
    }
}


