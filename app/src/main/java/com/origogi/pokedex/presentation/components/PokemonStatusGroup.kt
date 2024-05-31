package com.origogi.pokedex.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.origogi.pokedex.R
import com.origogi.pokedex.domain.model.PokemonDetailInfo
import com.origogi.pokedex.domain.model.PokemonType
import com.origogi.pokedex.domain.model.heightString
import com.origogi.pokedex.domain.model.weightString

@Composable
fun PokemonStatusGroup(pokemonDetailInfo: PokemonDetailInfo) {

    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp), // 아이템 간격 설정
        ) {
            PokemonStatus(
                modifier = Modifier.weight(1f),
                icon = R.drawable.icon_weight,
                label = "WEIGHT",
                pokemonDetailInfo.weightString
            )
            PokemonStatus(
                modifier = Modifier.weight(1f),

                icon = R.drawable.icon_height,
                label = "HEIGHT",
                pokemonDetailInfo.heightString,
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp), // 아이템 간격 설정
        ) {
            PokemonStatus(
                modifier = Modifier.weight(1f),

                icon = R.drawable.icon_category,
                label = "CATEGORY",
                pokemonDetailInfo.category
            )
            PokemonStatus(
                modifier = Modifier.weight(1f),

                icon = R.drawable.icon_ability,
                label = "ABILITIES",
                pokemonDetailInfo.abilities.joinToString("\n"),
            )
        }
    }
}

@Composable
fun PokemonStatus(modifier: Modifier = Modifier, icon: Int, label: String, value: String) {

    Column(modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.size(6.dp))
            // Text
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = Color.Black.copy(alpha = 0.6f)
            )
        }
        Spacer(modifier = Modifier.size(5.dp))
        Box(
            modifier =
            Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color.Black.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(15.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = value, style = MaterialTheme.typography.labelLarge,
                color = Color.Black.copy(alpha = 0.9f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonStatusGroupPreview() {
    PokemonStatusGroup(
        pokemonDetailInfo = PokemonDetailInfo(
            pokedexId = 6,
            name = "Bulbasaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/6.gif",
            animatedImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/6.gif",

            types = listOf(
                PokemonType.Fire,
                PokemonType.Water
            ),
            height = 7.0,
            weight = 69.0,
            desc = "It is a small quadruped Pokémon that has blue fur with a white face and paws. It has rounded ears with pink insides, big blue eyes, and a small black nose. Its paws each have three toes, with the outer two being larger than the inner one. It also has a long, curled tail.",
            abilities = listOf("Blaze", "Solar Power"),
            category = "Lizard Pokémon",
            genderRatio = 0.875,
            weaknessTypes = listOf(PokemonType.Water, PokemonType.Electric)
        )
    )
}