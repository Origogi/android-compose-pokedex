@file:OptIn(ExperimentalFoundationApi::class)

package com.origogi.pokedex.presentation.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.origogi.pokedex.domain.model.PokemonCardInfo
import com.origogi.pokedex.presentation.router.LocalNavScreenController
import com.origogi.pokedex.presentation.router.NavRoutes

@Composable
fun PokemonCardListView(
    modifier: Modifier = Modifier,
    pokemonCardInfoList: List<PokemonCardInfo>,
    needLoadMore: Boolean,
    onListEnd: () -> Unit = {},
) {

    val listState = rememberLazyListState()
    val navController = LocalNavScreenController.current

    Box(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        LazyColumn(
            state = listState,
        ) {
            items(pokemonCardInfoList, key = {
                it.pokedexId
            }) { pokemonCardInfo ->

                Box(
                    Modifier
                        .animateItemPlacement(
                            animationSpec = tween(500)
                        )
                        .padding(vertical = 8.dp)
                        .clickable {
                            navController.navigate(NavRoutes.PokemonDetail.route + "/${pokemonCardInfo.pokedexId}")
                        }
                ) {
                    PokemonCard(pokemonCardInfo)
                }
            }
            if (pokemonCardInfoList.isNotEmpty() && needLoadMore) {
                item {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingIndicator {
                            onListEnd()
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun LoadingIndicator(onAppear: () -> Unit) {
    LaunchedEffect(Unit) {
        // 컴포저블이 처음 나타날 때 실행할 코드
        onAppear()
    }
    Box(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(20.dp)
        )
    }
}


//@Preview(name = "PokemonCardListView", showBackground = true)
//@Composable
//private fun PreviewPokemonCardListView() {
//    PokedexTheme {
//        MyLocalProvider {
//            PokemonCardListView(
//                pokemonCardInfoList = listOf(
//                    PokemonCardInfo(
//                        pokedexId = 1,
//                        name = "Bulbasaur",
//                        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
//                        types = listOf(PokemonType.Bug, PokemonType.Dark),
//                    ),
//                    PokemonCardInfo(
//                        pokedexId = 12,
//                        name = "Bulbasaur",
//                        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
//                        types = listOf(PokemonType.Ice, PokemonType.Dark),
//                    ),
//
//                    ),
//                needLoadMore = false,
//            )
//        }
//
//    }
//}

