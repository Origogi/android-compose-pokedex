package com.origogi.pokedex.domain.model

data class PokemonDetailInfo(
    val pokedexId : Int,
    val name : String,
    val imageUrl : String,
    val animatedImageUrl : String,
    val types : List<PokemonType>,
    val height : Double,
    val weight : Double,
    val desc : String,

    )

val PokemonDetailInfo.mainType : PokemonType
    get() = types.first()
