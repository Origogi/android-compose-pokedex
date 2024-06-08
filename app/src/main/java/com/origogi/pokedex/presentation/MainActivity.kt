package com.origogi.pokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.origogi.pokedex.domain.model.RegionType
import com.origogi.pokedex.presentation.router.LocalNavScreenController
import com.origogi.pokedex.presentation.router.NavRoutes
import com.origogi.pokedex.presentation.screen.MainScreen
import com.origogi.pokedex.presentation.screen.PokemonDetailScreen
import com.origogi.pokedex.presentation.screen.RegionDetailScreen
import com.origogi.pokedex.presentation.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexTheme {
                MyApp()
            }
        }
    }
}

@Composable
private fun MyApp() {

    MyLocalProvider {
        MyNavHost()
    }
}

@Composable
fun MyLocalProvider(content: @Composable () -> Unit) {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavScreenController provides navController) {
        content()
    }
}

@Composable
fun MyNavHost() {
    val navController = LocalNavScreenController.current

    NavHost(navController = navController, startDestination = NavRoutes.Home.route) {
        composable(NavRoutes.Home.route) {
            MainScreen()
        }
        composable(NavRoutes.PokemonDetail.route + "/{pokedexId}",
            enterTransition = {
                // Define enter transition

                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(durationMillis = 300)
                ) + fadeIn(animationSpec = tween(durationMillis = 300))
            }, popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(durationMillis = 300)
                ) + fadeOut(animationSpec = tween(durationMillis = 300))
            }) {
            PokemonDetailScreen()
        }

        composable(NavRoutes.RegionDetail.route + "/{region}",
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(durationMillis = 300)
                ) + fadeIn(animationSpec = tween(durationMillis = 300))
            }, popEnterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(durationMillis = 300)
                ) + fadeOut(animationSpec = tween(durationMillis = 300))
            }) {
            val region = it.arguments?.getString("region").let { name ->
                RegionType.valueOf(name!!)
            }

            RegionDetailScreen(region)
        }
    }
}
