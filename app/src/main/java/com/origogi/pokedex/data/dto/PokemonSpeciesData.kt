package com.origogi.pokedex.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpeciesData(
    @SerialName(value = "flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry>,
    val genera: List<GeneraData>,
    @SerialName("gender_rate")
    val genderRate: Int,
    @SerialName(value = "evolution_chain")
    val evolutionChain : UrlData
)

@Serializable
data class FlavorTextEntry(

    @SerialName(value = "flavor_text")
    val flavorText: String,
    val language: NameUrlData,
)

@Serializable
data class GeneraData(
    val genus: String,
    val language: NameUrlData
)

fun PokemonSpeciesData.getEnglishGenusText(): String {
    return genera.first {
        it.language.name == "en"
    }.genus
}

fun PokemonSpeciesData.getEnglishFlavorText(): String {
    return flavorTextEntries.first {
        it.language.name == "en"
    }.flavorText
}