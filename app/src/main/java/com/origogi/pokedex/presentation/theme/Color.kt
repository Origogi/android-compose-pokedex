package com.origogi.pokedex.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val DeepSkyBlue = Color(0xFF173EA5)

val Black80 = Color(0xFFF2F2F2)
val Black100 = Color(0xFFE6E6E6)
val Black200 = Color(0xFFCCCCCC)
val Black700 = Color(0xFF4D4D4D)
val Black800 = Color(0xFF333333)
val Black900 = Color(0xFF1A1A1A)
val Male = Color(0xFF2441C3)
val Female = Color(0xFFFF7596)

val RedDelete = Color(0xFFCD3131)

fun Color.isDark(): Boolean {
    val luminance = this.luminance()
    return luminance < 0.5
}