package com.origogi.pokedex.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.origogi.pokedex.R
import com.origogi.pokedex.domain.model.PokemonCardInfo
import com.origogi.pokedex.domain.model.PokemonEvolutionChainInfo
import com.origogi.pokedex.domain.model.PokemonType
import com.origogi.pokedex.domain.model.toPokemonCardInfoList
import com.origogi.pokedex.presentation.theme.Black100
import com.origogi.pokedex.presentation.theme.PokedexTheme

@Composable
fun PokemonEvolutionInfoView(info: PokemonEvolutionChainInfo) {

    val list = info.toPokemonCardInfoList()

    Column(Modifier.fillMaxWidth()) {
        Text(text = "Evolutions", style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Black100,
                    ), shape = RoundedCornerShape(15.dp)
                )
        ) {

            if (list.size > 1) {
                Column(
                    Modifier.padding(horizontal = 16.dp, vertical = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    for ((i, item) in list.withIndex()) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            PokemonSmallCard(cardInfo = item)
                            if (i != list.lastIndex) {
                                Spacer(modifier = Modifier.height(8.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.icon_arrow_down),
                                    contentDescription = ""
                                )

                            }
                        }
                    }


                }
            } else {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "This Pokémon does not evolve.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    PokemonSmallCard(cardInfo = list.first())
                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonEvolutionInfoViewOneItemPreview() {
    PokedexTheme {
        PokemonEvolutionInfoView(
            info = PokemonEvolutionChainInfo(
                cardInfo = PokemonCardInfo(
                    pokedexId = 1,
                    name = "Bulbasaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/1.png",
                    types = listOf(
                        PokemonType.Grass, PokemonType.Poison
                    ),
                ),
                next = null
            )
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PokemonEvolutionInfoViewPreview() {
    PokedexTheme {
        PokemonEvolutionInfoView(
            info = PokemonEvolutionChainInfo(
                cardInfo = PokemonCardInfo(
                    pokedexId = 1,
                    name = "Bulbasaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/1.png",
                    types = listOf(
                        PokemonType.Grass, PokemonType.Poison
                    ),
                ), next = PokemonEvolutionChainInfo(
                    cardInfo = PokemonCardInfo(
                        pokedexId = 2,
                        name = "Ivysaur",
                        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/2.png",
                        types = listOf(
                            PokemonType.Grass, PokemonType.Poison
                        ),
                    ), next = PokemonEvolutionChainInfo(
                        cardInfo = PokemonCardInfo(
                            pokedexId = 3,
                            name = "Venusaur",
                            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/3.png",
                            types = listOf(
                                PokemonType.Grass, PokemonType.Poison
                            ),
                        ), next = null
                    )
                )
            )
        )

    }
}