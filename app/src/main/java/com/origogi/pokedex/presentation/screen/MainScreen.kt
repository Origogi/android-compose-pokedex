package com.origogi.pokedex.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.origogi.pokedex.presentation.theme.PokedexTheme

@Composable
fun MainScreen() {
    Scaffold {
        Surface(Modifier.padding(it)) {
            Text(text = " Hello Pokedex!")

        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    PokedexTheme {
        MainScreen()
    }
}