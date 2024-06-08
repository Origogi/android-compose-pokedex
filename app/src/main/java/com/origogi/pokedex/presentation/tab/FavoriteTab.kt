package com.origogi.pokedex.presentation.tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.origogi.pokedex.domain.model.PokemonCardInfo
import com.origogi.pokedex.presentation.components.PokemonCardListView
import com.origogi.pokedex.presentation.theme.Black80
import com.origogi.pokedex.presentation.theme.Black800
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

    Column(Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = "Favorites",
                style = MaterialTheme.typography.titleMedium,
                color = Black800
            )
        }
        Divider(
            color = Black80,
        )
        PokemonCardListView(
            pokemonCardInfoList = list,
            needLoadMore = false
        )
    }
}

