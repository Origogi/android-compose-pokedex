package com.origogi.pokedex.presentation.tab

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.origogi.pokedex.R
import com.origogi.pokedex.domain.model.PokemonCardInfo
import com.origogi.pokedex.presentation.components.PokemonCard
import com.origogi.pokedex.presentation.components.SwipeableCard
import com.origogi.pokedex.presentation.router.LocalNavScreenController
import com.origogi.pokedex.presentation.theme.Black700
import com.origogi.pokedex.presentation.theme.Black80
import com.origogi.pokedex.presentation.theme.Black800
import com.origogi.pokedex.presentation.theme.PokedexTheme
import com.origogi.pokedex.presentation.viewmodel.FavoriteTabViewModel

@Composable
fun FavoriteTab(
    modifier: Modifier = Modifier,
    viewModel: FavoriteTabViewModel = hiltViewModel()
) {

    val list by viewModel.list.collectAsState()
    Column(modifier.fillMaxSize()) {
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
        if (list.isEmpty()) {
            EmptyPlaceholder()
        } else {
            Content(list, onDeleteItem = {
                viewModel.removeFavorite(it)

            })
        }
    }
}

@Composable
private fun EmptyPlaceholder() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.image_no_favorite),
            modifier = Modifier.size(210.dp),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "You haven't favorited any Pokémon :(",
            style = MaterialTheme.typography.headlineLarge,
            color = Black800,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Click on the heart icon of your favorite Pokémon and they will appear here.",
            style = MaterialTheme.typography.bodyMedium,
            color = Black700,
            textAlign = TextAlign.Center
        )

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(list: List<PokemonCardInfo>, onDeleteItem: (Int) -> Unit) {

    val listState = rememberLazyListState()
    val navController = LocalNavScreenController.current

    LazyColumn(
        state = listState
    ) {
        items(list, {
            it.pokedexId

        }) { pokemonCardInfo ->
            Box(
                Modifier
                    .animateItemPlacement(
                        animationSpec = tween(500)
                    )
                    .padding(vertical = 8.dp)

            ) {
                SwipeableCard(
                    onTapDeleteButton = {
                        onDeleteItem(pokemonCardInfo.pokedexId)
                    },
                    content = {
                        PokemonCard(pokemonCardInfo)
                    },
                    onTapContent = {
                        navController.navigate("pokemonDetail/${pokemonCardInfo.pokedexId}")
                    }
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun NoFavoritesPreview() {
    PokedexTheme {
        EmptyPlaceholder()

    }
}
