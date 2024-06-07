package com.origogi.pokedex.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.origogi.pokedex.domain.model.PokemonCardInfo
import com.origogi.pokedex.domain.model.PokemonType
import com.origogi.pokedex.domain.model.mainType
import com.origogi.pokedex.extenstion.PokedexIdString
import com.origogi.pokedex.presentation.theme.Black800
import com.origogi.pokedex.presentation.theme.PokedexTheme
import com.origogi.pokedex.presentation.theme.isDark

@Composable
fun PokemonCard(pokemonCardInfo: PokemonCardInfo) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(102.dp)
            .background(
                pokemonCardInfo.mainType.bgColor,
                shape = RoundedCornerShape(15.dp)
            )
    ) {

        Row(modifier = Modifier) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = pokemonCardInfo.pokedexId.PokedexIdString(),
                    style = MaterialTheme.typography.headlineSmall, color = Black800
                )
                Text(
                    text = pokemonCardInfo.name,
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                PokemonTypeChipRow(pokemonCardInfo.types)

            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                Modifier
                    .fillMaxHeight()
                    .width(126.dp)
                    .background(
                        color = pokemonCardInfo.mainType.color,
                        shape = RoundedCornerShape(15.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                PokemonTypeIcon(type = pokemonCardInfo.mainType, modifier = Modifier.size(96.dp))

                AsyncImage(
                    model = pokemonCardInfo.imageUrl,
                    contentDescription = "",
                    modifier = Modifier.size(94.dp),

                    )
            }
        }

        RoundLikeButton(
            modifier = Modifier
                .padding(6.dp)
                .align(Alignment.TopEnd),
            pokedexId = pokemonCardInfo.pokedexId
        )
    }

}

@Composable
private fun PokemonTypeChipRow(types: List<PokemonType>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        types.forEach {
            PokemonTypeChip(it)
        }
    }
}

@Composable
private fun PokemonTypeChip(type: PokemonType) {
    Box(
        modifier = Modifier
            .background(
                type.color,
                shape = RoundedCornerShape(48.dp)
            )
            .padding(horizontal = 6.dp, vertical = 3.dp),
        contentAlignment = Alignment.Center
    ) {
        Row {
            Box(
                Modifier
                    .size(20.dp)
                    .background(Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = type.iconAssetId),
                    contentDescription = "",
                    modifier = Modifier.size(13.dp),
                    colorFilter = ColorFilter.tint(type.color)
                )

            }
            Text(
                text = type.label,
                style = MaterialTheme.typography.bodySmall,
                color = if (type.color.isDark()) Color.Black else Color.White,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonTypeChip() {
    PokedexTheme {
        PokemonTypeChip(PokemonType.Grass)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonTypeChipRow() {
    PokedexTheme {
        PokemonTypeChipRow(listOf(PokemonType.Grass, PokemonType.Poison))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonCard() {
    PokedexTheme {
        PokemonCard(
            PokemonCardInfo(
                1,
                "Bulbasaur",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                listOf(PokemonType.Grass, PokemonType.Poison)
            )
        )
    }
}