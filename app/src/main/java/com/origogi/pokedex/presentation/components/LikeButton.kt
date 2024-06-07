package com.origogi.pokedex.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.origogi.pokedex.R
import com.origogi.pokedex.presentation.viewmodel.LikeButtonViewModel

@Composable
fun RoundLikeButton(
    modifier: Modifier = Modifier,
    pokedexId: Int,
    viewModel: LikeButtonViewModel = hiltViewModel<LikeButtonViewModel,
            LikeButtonViewModel.LikeButtonViewModelFactory>(
        key = "RoundLikeButtonViewModel_$pokedexId"
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
        isFav = isFav,
        onIcon = R.drawable.icon_fav_round_on,
        offIcon = R.drawable.icon_fav_round_off,
    )
}

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
    val interactionSource = remember { MutableInteractionSource() }

    ButtonIcon(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                viewModel.toggle()
            },
        isFav = isFav,
        onIcon = R.drawable.icon_fav_on,
        offIcon = R.drawable.icon_fav_off,
    )
}

@Composable
private fun ButtonIcon(modifier: Modifier, isFav: Boolean, offIcon: Int, onIcon: Int) {
    Image(
        painter = painterResource(id = if (isFav) onIcon else offIcon),
        contentDescription = "Favorite",
        modifier = modifier
    )
}