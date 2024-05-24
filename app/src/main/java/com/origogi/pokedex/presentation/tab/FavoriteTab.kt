package com.origogi.pokedex.presentation.tab

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FavoriteTab(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "FavoriteTab")
    }
}

@Preview(name = "FavoriteTab")
@Composable
private fun PreviewFavoriteTab() {
    FavoriteTab()
}