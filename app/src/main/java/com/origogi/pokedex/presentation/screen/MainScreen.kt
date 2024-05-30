package com.origogi.pokedex.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.origogi.pokedex.presentation.components.BottomNavigationBar
import com.origogi.pokedex.presentation.components.MainTabInfo
import com.origogi.pokedex.presentation.tab.FavoriteTab
import com.origogi.pokedex.presentation.tab.PokedexTab
import com.origogi.pokedex.presentation.tab.ProfileTab
import com.origogi.pokedex.presentation.tab.RegionTab
import com.origogi.pokedex.presentation.theme.PokedexTheme

@Composable
fun MainScreen() {
    val tabNavController = rememberNavController()

    Scaffold(
        content = {
            MainScreenContent(navController = tabNavController, modifier = Modifier.padding(it))
        },
        bottomBar = {
            BottomNavigationBar(tabNavController)
        }
    )
}

@Composable
fun MainScreenContent(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = MainTabInfo.Pokedex.route,
        modifier = modifier
    ) {
        composable(MainTabInfo.Pokedex.route) {
            PokedexTab()
        }
        composable(MainTabInfo.Region.route) {
            RegionTab()
        }
        composable(MainTabInfo.Favorite.route) {
            FavoriteTab()
        }
        composable(MainTabInfo.Profile.route) {
            ProfileTab()
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