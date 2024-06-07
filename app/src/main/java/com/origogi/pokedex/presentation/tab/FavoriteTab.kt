package com.origogi.pokedex.presentation.tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.origogi.pokedex.domain.model.PokemonCardInfo
import com.origogi.pokedex.presentation.components.PokemonCardListView
import com.origogi.pokedex.presentation.viewmodel.FavoriteTabViewModel

@Composable
fun FavoriteTab(
    modifier: Modifier = Modifier,
    viewModel : FavoriteTabViewModel = hiltViewModel()
) {

    val list by viewModel.list.collectAsState()
    Box(modifier.fillMaxSize()) {
        Body(list = list)
    }
}

@Composable
private fun Body(list: List<PokemonCardInfo>) {

    PokemonCardListView(
        pokemonCardInfoList = list,
        needLoadMore = false
    )
}

