package com.origogi.pokedex.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.origogi.pokedex.R

@Composable
fun LikeButton(modifier: Modifier = Modifier) {
    Image(painter = painterResource(id = R.drawable.icon_fav_off), contentDescription = "Favorite",
        modifier = modifier
            .size(32.dp)
    )
}