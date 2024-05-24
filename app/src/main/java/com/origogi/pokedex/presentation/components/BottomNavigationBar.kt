package com.origogi.pokedex.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.origogi.pokedex.R
import com.origogi.pokedex.presentation.theme.DeepSkyBlue
import com.origogi.pokedex.presentation.theme.PokedexTheme


@Composable

fun BottomNavigationBar() {

}

@Composable
fun BottomNavigationBarItem(isSelected: Boolean) {
    Column(
        Modifier.size(56.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Crossfade(targetState = isSelected, label = "icon crossfade",) {
            val icon = if (it) R.drawable.icon_pokedex_on else R.drawable.icon_pokedex_off

            Image(
                painter = painterResource(id = icon),
                contentDescription = "Pokedéx",
                modifier = Modifier.size(24.dp)
            )
        }
        AnimatedVisibility(
            visible = isSelected,

            ) {
            Text(
                "Pokedéx",
                style = MaterialTheme.typography.labelSmall,
                color = DeepSkyBlue
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarItemPreview() {
    var state by remember {
        mutableStateOf(false)
    }

    PokedexTheme {
        Box(modifier = Modifier.clickable {
            state = !state
        }) {
            BottomNavigationBarItem(state)
        }


    }
}