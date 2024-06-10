package com.origogi.pokedex.domain.model

data class PokemonEvolutionChainInfo(
    val cardInfo: PokemonCardInfo,
    val next : PokemonEvolutionChainInfo?
)

fun PokemonEvolutionChainInfo.toPokemonCardInfoList(): List<PokemonCardInfo> {
    val list = mutableListOf<PokemonCardInfo>()
    var current : PokemonEvolutionChainInfo? = this
    while (current != null) {
        list.add(current.cardInfo)
        current = current.next
    }
    return list
}
