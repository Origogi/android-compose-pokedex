package com.origogi.pokedex.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.origogi.pokedex.domain.model.RegionType
import com.origogi.pokedex.extenstion.capitalizeFirstChar
import com.origogi.pokedex.presentation.components.PokemonCardListView
import com.origogi.pokedex.presentation.theme.Black80
import com.origogi.pokedex.presentation.theme.Black800
import com.origogi.pokedex.presentation.viewmodel.PokemonCardListViewModel
import com.origogi.pokedex.presentation.viewmodel.ViewModelState

@Composable
fun RegionDetailScreen(
    regionType: RegionType,
    viewModel: PokemonCardListViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.loadMore()
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Box(
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = regionType.name.capitalizeFirstChar(),
                    style = MaterialTheme.typography.titleMedium,
                    color = Black800
                )
            }
            Divider(
                color = Black80,
            )

            if (viewModel.list.isEmpty() && viewModel.state == ViewModelState.Loading) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(35.dp)
                    )
                }
            }

            PokemonCardListView(
                pokemonCardInfoList = viewModel.list,
                needLoadMore = viewModel.needLoadMore
            ) {
                viewModel.loadMore()
            }

        }
    }

}
