package com.origogi.pokedex.domain.model

data class PokemonInfo(
    val pokedexId : Int,
    val name : String,
    val imageUrl : String,
    val gifImageUrl : String,
    val types : List<PokemonType>,
    val height : Double,
    val weight : Double,
    val abilities : List<String>,
)

