package com.origogi.pokedex.presentation.router

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object PokemonDetail : NavRoutes("pokemonDetail")
}