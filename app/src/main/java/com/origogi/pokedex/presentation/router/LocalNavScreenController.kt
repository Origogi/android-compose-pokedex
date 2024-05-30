package com.origogi.pokedex.presentation.router

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

val LocalNavScreenController = compositionLocalOf<NavHostController> {
    error("LocalNavScreenController not provided")
}