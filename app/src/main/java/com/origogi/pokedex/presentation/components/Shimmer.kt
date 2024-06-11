package com.origogi.pokedex.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas

@Composable
fun shimmerTransitionAnimation() : State<Float> {
    val transition = rememberInfiniteTransition(label = "")
    val shimmerTranslateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    return shimmerTranslateAnim
}

fun Modifier.shimmerEffect(shimmerTranslateAnim: Float): Modifier = this.then(
    Modifier.drawWithContent {
        with(drawContext.canvas.nativeCanvas) {
            val checkPoint = saveLayer(null, null)

            val shimmerBrush = Brush.linearGradient(
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