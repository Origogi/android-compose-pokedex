package com.origogi.pokedex.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpeciesData(
    @SerialName(value = "flavor_text_entries")
    val flavorTextEntries : List<FlavorTextEntry>
)

@Serializable
data class FlavorTextEntry(

    @SerialName(value = "flavor_text")
    val flavorText : String,
    val language : NameUrlData
)

fun PokemonSpeciesData.getEnglishFlavorText() : String {
    return flavorTextEntries.first {
        it.language.name == "en"
    }.flavorText
}