package com.origogi.pokedex.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonData(
    val id : Int,
    val name : String,
    val types : List<PokemonTypeData>,
    val sprites : PokemonSpritesData
)
@Serializable
data class PokemonTypeData(
    val slot : Int,
    val type : NameUrlData
)

@Serializable
data class PokemonSpritesData(

    @SerialName(value = "front_default")
    val frontDefault : String
)