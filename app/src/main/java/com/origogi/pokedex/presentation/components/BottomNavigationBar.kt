package com.origogi.pokedex.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.origogi.pokedex.R
import com.origogi.pokedex.presentation.theme.DeepSkyBlue
import com.origogi.pokedex.presentation.theme.PokedexTheme


sealed class MainTabInfo(
    val iconOn: Int,
    val iconOff: Int,
    val label: String,
    val route: String
) {
    data object Pokedex : MainTabInfo(
        iconOn = R.drawable.icon_pokedex_on,
        iconOff = R.drawable.icon_pokedex_off,
        label = "Pokedéx",
        route = "pokedex"
    )

    data object Region : MainTabInfo(
        iconOn = R.drawable.icon_region_on,
        iconOff = R.drawable.icon_region_off,
        label = "Region",
        route = "region"
    )

    data object Favorite : MainTabInfo(
        iconOn = R.drawable.icon_favorite_on,
        iconOff = R.drawable.icon_favorite_off,
        label = "Favorite",
        route = "favorite"
    )

    data object Profile : MainTabInfo(
        iconOn = R.drawable.icon_profile_on,
        iconOff = R.drawable.icon_profile_off,
        label = "Profile",
        route = "profile"
    )

}

@Composable

fun BottomNavigationBar(navController: NavHostController) {

    val buttons = listOf(
        MainTabInfo.Pokedex,
        MainTabInfo.Region,
        MainTabInfo.Favorite,
        MainTabInfo.Profile
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.White,
        modifier = Modifier.fillMaxWidth(),
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            buttons.forEachIndexed { index, button ->
                Box(Modifier.clickable {
                    navController.navigate(button.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }) {
                    BottomNavigationBarItem(
                        isSelected = currentRoute == button.route,
                        iconOn = button.iconOn,
                        iconOff = button.iconOff,
                        label = button.label
                    )
                }
            }
        }

    }
}

@Composable
fun BottomNavigationBarItem(isSelected: Boolean, iconOn: Int, iconOff: Int, label: String) {
    Column(
        Modifier.size(56.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Crossfade(targetState = isSelected, label = "icon crossfade $label") {
            val icon = if (it) iconOn else iconOff

            Image(
                painter = painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier.size(24.dp)
            )
        }
        AnimatedVisibility(
            visible = isSelected,

            ) {
            Text(
                label,
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

    val button = MainTabInfo.Region

    PokedexTheme {
        Box(modifier = Modifier.clickable {
            state = !state
        }) {
            BottomNavigationBarItem(
                state,
                iconOn = button.iconOn,
                iconOff = button.iconOff,
                label = button.label
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun BottomNavigationBarPreview() {
//    PokedexTheme {
//        BottomNavigationBar()
//    }
//}