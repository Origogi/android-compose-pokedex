package com.origogi.pokedex.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.painterResource
import com.origogi.pokedex.domain.model.PokemonType

@Composable
fun PokemonTypeIcon(modifier: Modifier = Modifier, type : PokemonType) {
    Image(
        painter = painterResource(id = type.iconAssetId),
        contentDescription = "",
        modifier = modifier
            .drawWithContent {
                with(drawContext.canvas.nativeCanvas) {
                    val checkPoint = saveLayer(null, null)

                    val brush = Brush.linearGradient(
                        colors = listOf(
                            Color.White,
                            type.color
                        ),
                        start = Offset.Zero,
                        end = Offset(size.width, size.height)
                    )

                    // Destination
                    drawContent()

                    // Source
                    drawRect(
                        brush = brush,
                        blendMode = BlendMode.SrcIn
                    )

                    restoreToCount(checkPoint)

                }
            },

        )
}