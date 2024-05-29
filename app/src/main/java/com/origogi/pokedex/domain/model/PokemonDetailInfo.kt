package com.origogi.pokedex.domain.model

data class PokemonDetailInfo(
    val pokedexId : Int,
    val name : String,
    val imageUrl : String,
    val types : List<PokemonType>,
    val height : Int,
    val weight : Int,
)

// main type
val PokemonDetailInfo.mainType : PokemonType
    get() = types.first()
