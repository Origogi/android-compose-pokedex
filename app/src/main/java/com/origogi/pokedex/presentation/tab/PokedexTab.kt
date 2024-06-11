package com.origogi.pokedex.presentation.tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        && viewModel.state == ViewModelState.Loading
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier.size(35.dp)
            )
        }
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


