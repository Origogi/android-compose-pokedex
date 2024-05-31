package com.origogi.pokedex.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypeDetailData(
    @SerialName("damage_relations")
    val damageRelations: DamageRelationsData,
)

@Serializable
data class DamageRelationsData(
    @SerialName("double_damage_from")
    val doubleDamageFrom: List<NameUrlData>,
)
