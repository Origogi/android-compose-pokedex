package com.origogi.pokedex.presentation.screen

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    val inAnimation = scaleIn(animationSpec = tween(300), initialScale = 0.92f) + fadeIn(animationSpec = tween(300))
    val outAnimation = scaleOut(animationSpec = tween(300), targetScale = 0.92f) + fadeOut(animationSpec = tween(300))

    NavHost(
        navController = navController,
        startDestination = MainTabInfo.Pokedex.route,
        modifier = modifier
    ) {
        composable(MainTabInfo.Pokedex.route,
            enterTransition = {
                inAnimation
            },
            exitTransition = {
                outAnimation
            }
        ) {
            PokedexTab()
        }
        composable(MainTabInfo.Region.route,
            enterTransition = {
                inAnimation
            },
            exitTransition = {

                outAnimation
            }) {
            RegionTab()
        }
        composable(MainTabInfo.Favorite.route,
            enterTransition = {
        inAnimation
            },
            exitTransition = {
outAnimation
            }) {
            FavoriteTab()
        }
        composable(MainTabInfo.Profile.route,
            enterTransition = {
inAnimation
            },
            exitTransition = {
outAnimation
            }) {
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