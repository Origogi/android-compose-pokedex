package com.origogi.pokedex.extenstion

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas

fun String.capitalizeFirstChar(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}

fun Int.pokedexIdString(): String {
    return "Nº${this.toString().padStart(3, '0')}"
}

fun Modifier.shimmerEffect(shimmerTranslateAnim: Float): Modifier = this.then(
    Modifier.drawWithContent {
        with(drawContext.canvas.nativeCanvas) {
            val checkPoint = saveLayer(null, null)

            val shimmerBrush = androidx.compose.ui.graphics.Brush.linearGradient(
                colors = listOf(
                    Color.LightGray.copy(alpha = 0.9f),
                    Color.LightGray.copy(alpha = 0.3f),
                    Color.LightGray.copy(alpha = 0.9f)
                ),
                start = Offset(
                    shimmerTranslateAnim - 200f,
                    shimmerTranslateAnim - 200f
                ),
                end = Offset(shimmerTranslateAnim, shimmerTranslateAnim)
            )

            // Destination
            drawContent()

            // Source
            drawRect(
                brush = shimmerBrush,
                blendMode = BlendMode.SrcIn
            )

            restoreToCount(checkPoint)
        }
    }
)