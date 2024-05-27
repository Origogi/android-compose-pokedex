package com.origogi.pokedex.domain.model

data class PokemonCardInfo(
    val pokedexId : Int,
    val name : String,
    val imageUrl : String,
    val types : List<PokemonType>
)

val PokemonCardInfo.mainType : PokemonType
    get() = types.first()

val PokemonCardInfo.pokedexIdString : String
    get() = "Nº${pokedexId.toString().padStart(3, '0')}"