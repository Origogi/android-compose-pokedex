package com.origogi.pokedex.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.origogi.pokedex.R
import com.origogi.pokedex.presentation.theme.Female
import com.origogi.pokedex.presentation.theme.Male
import com.origogi.pokedex.presentation.theme.PokedexTheme
import java.text.DecimalFormat

@Composable
fun PokemonGenderRatioView(ratio: Double?) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Gender", style = MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(49.dp))

        ) {
            if (ratio == null) {
                StripedProgressBar()
            } else {
                Row {
                    GenderRatioBar(
                        ratio = ratio.toFloat(),
                        color = Male
                    )
                    GenderRatioBar(
                        ratio = (1.0 - ratio).toFloat(),
                        color = Female
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))

        if (ratio == null) {
            Text("Unknown", style = MaterialTheme.typography.labelMedium)
        } else {
            Row {

                Image(
                    painter = painterResource(R.drawable.icon_male),
                    contentDescription = "",
                )
                Spacer(modifier = Modifier.width(3.dp))
                GenderRatioText(ratio)

                Spacer(Modifier.weight(1f))

                Image(
                    painter = painterResource(R.drawable.icon_female),
                    contentDescription = "",
                )
                Spacer(modifier = Modifier.width(3.dp))
                GenderRatioText(ratio)
            }
        }
    }
}

@Composable
fun GenderRatioText(ratio: Double) {
    val percentage = (1.0 - ratio) * 100
    val decimalFormat = DecimalFormat("#.##")
    val percentageText = decimalFormat.format(percentage)

    Text(
        text = "$percentageText%",
        style = MaterialTheme.typography.labelMedium
    )
}

@Composable
private fun RowScope.GenderRatioBar(ratio : Float, color: Color) {
    if (ratio > 0f) {
        Box(
            modifier = Modifier
                .weight(ratio)
                .fillMaxSize()
                .background(color)
        )
    }
}

@Composable
fun StripedProgressBar(
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .border(1.dp, Color.Black.copy(alpha = 0.1f), RoundedCornerShape(25.dp))
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Draw the background with rounded corners
            val stripeColor = Color(0xFFE6E6E6)
            // Draw the striped pattern
            val stripeWidth = 1.dp.toPx()
            val stripeSpacing = 10.dp.toPx()
            val totalWidth = size.width

            var currentX = -stripeWidth
            while (currentX < totalWidth) {
                drawLine(
                    color = stripeColor,
                    start = Offset(currentX, 0f),
                    end = Offset(currentX - 20, size.height),
                    strokeWidth = stripeWidth
                )
                currentX += (stripeWidth + stripeSpacing)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonGenderRatioViewPreview() {
    PokedexTheme {
        PokemonGenderRatioView(0.5)
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonGenderRatioViewAllPreview() {
    PokedexTheme {
        PokemonGenderRatioView(0.0)
    }
}


@Preview(showBackground = true)
@Composable
fun PokemonGenderRatioViewDefaultPreview() {
    PokedexTheme {
        PokemonGenderRatioView(null)
    }
}