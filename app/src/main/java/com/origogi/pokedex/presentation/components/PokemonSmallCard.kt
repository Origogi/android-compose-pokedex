package com.origogi.pokedex.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.origogi.pokedex.domain.model.PokemonCardInfo
import com.origogi.pokedex.domain.model.PokemonType
import com.origogi.pokedex.domain.model.mainType
import com.origogi.pokedex.extenstion.PokedexIdString
import com.origogi.pokedex.presentation.theme.Black100
import com.origogi.pokedex.presentation.theme.Black700
import com.origogi.pokedex.presentation.theme.Black900

@Composable
fun PokemonSmallCard(cardInfo: PokemonCardInfo) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(76.dp)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = Black100,
                ), shape = RoundedCornerShape(90.dp)
            )
    ) {
        Row {
            Box(
                Modifier
                    .size(96.dp, 74.dp)
                    .clip(RoundedCornerShape(71.dp))
                    .background(cardInfo.mainType.color),
                contentAlignment = Alignment.Center
            ) {
                PokemonTypeIcon(Modifier.size(65.dp, 65.dp), type = cardInfo.mainType)
                AsyncImage(
                    model = cardInfo.imageUrl,
                    contentDescription = "",
                    modifier = Modifier
                        .size(96.dp, 74.dp)
                        .padding(4.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = cardInfo.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = Black900
                )
                Text(
                    text = cardInfo.pokedexId.PokedexIdString(),
                    style = MaterialTheme.typography.labelSmall,
                    color = Black700
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    for (type in cardInfo.types) {
                        TypeChip(
                            modifier = Modifier.weight(1f),
                            type
                        )
                    }
                    Spacer(modifier = Modifier.width(44.dp))
                }

            }

        }
    }
}

@Composable
private fun TypeChip(modifier: Modifier, type: PokemonType) {
    Box(
        modifier = modifier

            .clip(RoundedCornerShape(15.dp))
            .background(type.color)
            .padding(vertical = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = type.iconAssetId),
            contentDescription = "",
            modifier = Modifier.size(10.dp)
        )
    }
}