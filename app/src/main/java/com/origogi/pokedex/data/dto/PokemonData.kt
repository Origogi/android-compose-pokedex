package com.origogi.pokedex.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonData(
    val id: Int,
    val name: String,
    val types: List<PokemonTypeData>,
    val sprites: PokemonSpritesData,
    val abilities: List<PokemonAbilityData>,
    val weight: Double,
    val height: Double
)

@Serializable
data class PokemonTypeData(
    val slot: Int,
    val type: NameUrlData
)

@Serializable
data class PokemonSpritesData(

    @SerialName(value = "front_default")
    val frontDefault: String,
    val other: OtherData,
    val versions: VersionsData,
)

@Serializable
data class PokemonAbilityData(
    val ability: NameUrlData
)

@Serializable
data class VersionsData(
    @SerialName(value = "generation-viii")
    val generationViii: GenerationViiiData
)

@Serializable
data class GenerationViiiData(
    @SerialName(value = "icons")
    val icons: ImageUrlSetData
)

@Serializable
data class OtherData(
    val showdown: ImageUrlSetData
)

@Serializable
data class ImageUrlSetData(
    @SerialName(value = "front_default")
    val frontDefault: String
)

