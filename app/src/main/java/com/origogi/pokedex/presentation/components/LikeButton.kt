package com.origogi.pokedex.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.origogi.pokedex.R
import com.origogi.pokedex.presentation.viewmodel.LikeButtonViewModel

@Composable
fun LikeButton(
    modifier: Modifier = Modifier,
    pokedexId: Int,
    viewModel: LikeButtonViewModel = hiltViewModel<LikeButtonViewModel,
            LikeButtonViewModel.LikeButtonViewModelFactory>(
                key = "LikeButtonViewModel_$pokedexId"
            ) { factory ->
        factory.create(pokedexId)
    }
) {
    val isFav by viewModel.isFavorite.collectAsState()

    ButtonIcon(
        modifier = modifier
            .clip(CircleShape)
            .clickable {
            viewModel.toggle()
        },
        isFav = isFav
    )
}

@Composable
private fun ButtonIcon(modifier: Modifier, isFav: Boolean) {
    Image(
        painter = painterResource(id = if (isFav) R.drawable.icon_fav_round_on else R.drawable.icon_fav_round_off),
        contentDescription = "Favorite",
        modifier = modifier
            .size(32.dp)
    )
}