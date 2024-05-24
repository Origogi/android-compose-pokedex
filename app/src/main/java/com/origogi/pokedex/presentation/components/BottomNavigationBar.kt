package com.origogi.pokedex.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.origogi.pokedex.presentation.theme.PokedexTheme


@Composable

fun BottomNavigationBar() {

}

@Composable
fun BottomNavigationBarItem() {
    Column {

        Text("Pokedex", style = MaterialTheme.typography.labelSmall)
    }

}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarItemPreview() {
    PokedexTheme {
        BottomNavigationBarItem()

    }
}