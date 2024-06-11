package com.origogi.pokedex.extenstion

fun String.capitalizeFirstChar(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}

fun Int.pokedexIdString(): String {
    return "Nº${this.toString().padStart(3, '0')}"
}

