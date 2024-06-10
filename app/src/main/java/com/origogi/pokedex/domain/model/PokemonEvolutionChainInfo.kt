package com.origogi.pokedex.domain.model

data class PokemonEvolutionChainInfo(
    val cardInfo: PokemonCardInfo,
    val next : PokemonEvolutionChainInfo?
)
