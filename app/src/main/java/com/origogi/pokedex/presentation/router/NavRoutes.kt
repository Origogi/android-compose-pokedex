package com.origogi.pokedex.presentation.router

sealed class NavRoutes(val route: String) {
    data object Home : NavRoutes("home")
    data object PokemonDetail : NavRoutes("pokemonDetail")
    data object RegionDetail : NavRoutes("regionDetail")
}