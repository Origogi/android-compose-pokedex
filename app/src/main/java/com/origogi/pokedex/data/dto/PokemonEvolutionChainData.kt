package com.origogi.pokedex.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonEvolutionChainData(
    val chain : ChainData,
    val id : Int
)

@Serializable
data class ChainData(
    @SerialName("evolves_to")
    val evolvesTo : List<ChainData>,
    val species : NameUrlData
)