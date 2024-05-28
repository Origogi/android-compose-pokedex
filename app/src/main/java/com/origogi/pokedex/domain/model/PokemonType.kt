package com.origogi.pokedex.domain.model

import androidx.compose.ui.graphics.Color
import com.origogi.pokedex.R

enum class PokemonType(
    val iconAssetId : Int,
    val color : Color,
    val bgColor : Color,
    val label : String
) {
    Fire(
        iconAssetId = R.drawable.icon_type_fire,
        color = Color(0xFFFF9D55),
        bgColor = Color(0xFFFCF3EB),
        label = "Fire"
    ),
    Water(
        iconAssetId = R.drawable.icon_type_water,
        color = Color(0xFF5090D6),
        bgColor = Color(0xFFEBF1F8),
        label = "Water"
    ),

    Grass(
        iconAssetId = R.drawable.icon_type_grass,
        color = Color(0xFF63BC5A),
        bgColor = Color(0xFFF1F6E8),
        label = "Grass"
    ),

    Electric(
        iconAssetId = R.drawable.icon_type_electric,
        color = Color(0xFFF4D23C),
        bgColor = Color(0xFFFBF8E9),
        label = "Electric"
    ),

    Psychic(
        iconAssetId = R.drawable.icon_type_psychic,
        color = Color(0xFFFA7179),
        bgColor = Color(0xFFFCEEEF),
        label = "Psychic"
    ),

    Ice(
        iconAssetId = R.drawable.icon_type_ice,
        color = Color(0xFF73CEC0),
        bgColor = Color(0xFFF1FBF9),
        label = "Ice"
    ),

    Dragon(
        iconAssetId = R.drawable.icon_type_dragon,
        color = Color(0xFF0B6DC3),
        bgColor = Color(0xFFF1FBF9),
        label = "Dragon"
    ),

    Dark(
        iconAssetId = R.drawable.icon_type_dark,
        color = Color(0xFF5A5465),
        bgColor = Color(0xFFECEBED),
        label = "Dark"
    ),

    Fairy(
        iconAssetId = R.drawable.icon_type_fairy,
        color = Color(0xFFEC8FE6),
        bgColor = Color(0xFFECEBED),
        label = "Fairy"
    ),

     Normal(
        iconAssetId = R.drawable.icon_type_normal,
        color = Color(0xFF919AA2),
        bgColor = Color(0xFFF1F2F3),
        label = "Normal"
    ),

    Fighting (
        iconAssetId = R.drawable.icon_type_fighting,
        color = Color(0xFFCE416B),
        bgColor = Color(0xFFF8E9EE),
        label = "Fighting"
    ),

    Flying(
        iconAssetId = R.drawable.icon_type_flying,
        color = Color(0xFF8FA8DD),
        bgColor = Color(0xFFF1F4FA),
        label = "Flying"
    ),

     Poison(
        iconAssetId = R.drawable.icon_type_poison,
        color = Color(0xFFB567CE),
        bgColor = Color(0xFFF5EDF8),
        label = "Poison"
    ),

     Ground (
        iconAssetId = R.drawable.icon_type_ground,
        color = Color(0xFFD97845),
        bgColor = Color(0xFFF9EFEA),
        label = "Ground"
    ),

    Rock(
        iconAssetId = R.drawable.icon_type_rock,
        color = Color(0xFFC5B78C),
        bgColor = Color(0xFFF7F5F1),
        label = "Rock"
    ),

    Bug(
        iconAssetId = R.drawable.icon_type_bug,
        color = Color(0xFF91C12F),
        bgColor = Color(0xFFF1F6E8),
        label = "Bug"
    ),

    Ghost(
        iconAssetId = R.drawable.icon_type_ghost,
        color = Color(0xFF5269AD),
        bgColor = Color(0xFFEBEDF4),
        label = "Ghost"
    ),

    Steel(
        iconAssetId = R.drawable.icon_type_steel,
        color = Color(0xFF5A8EA2),
        bgColor = Color(0xFFECF1F3),
        label = "Steel"
    )
}