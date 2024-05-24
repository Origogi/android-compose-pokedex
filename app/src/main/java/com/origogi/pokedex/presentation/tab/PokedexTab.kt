package com.origogi.pokedex.presentation.tab

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PokedexTab(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "PokedexTab")
    }
}

@Preview(name = "PokedexTab")
@Composable
private fun PreviewPokedexTab() {
    PokedexTab()
}