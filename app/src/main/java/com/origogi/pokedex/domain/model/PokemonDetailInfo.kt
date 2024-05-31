package com.origogi.pokedex.domain.model

data class PokemonDetailInfo(
    val pokedexId: Int,
    val name: String,
    val imageUrl: String,
    val animatedImageUrl: String,
    val types: List<PokemonType>,
    val height: Double,
    val weight: Double,
    val desc: String,
    val abilities: List<String>,
    val category: String,
    val genderRatio : Double?,
    val weaknessTypes: List<PokemonType>
)

val PokemonDetailInfo.mainType: PokemonType
    get() = types.first()

val PokemonDetailInfo.heightString: String
    get() = "${height / 10.0} m"

val PokemonDetailInfo.weightString: String
    get() = "${weight / 10.0} kg"