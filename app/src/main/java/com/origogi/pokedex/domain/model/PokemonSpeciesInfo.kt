package com.origogi.pokedex.domain.model

data class PokemonSpeciesInfo(
    val desc : String,
    val category : String,
    val genderRate : Double?,
    val evolutionChainId : Int,
)
