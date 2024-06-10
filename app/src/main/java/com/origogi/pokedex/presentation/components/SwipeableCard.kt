package com.origogi.pokedex.presentation.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.origogi.pokedex.R
import com.origogi.pokedex.presentation.theme.RedDelete
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


enum class DragAnchors {
    Start,
    End,
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeableCard(
    content: @Composable () -> Unit,
    onTapContent: () -> Unit,
    onTapDeleteButton: () -> Unit,
) {
    val density = LocalDensity.current

    val state = remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.Start,
            positionalThreshold = { distance: Float -> distance * 0.5f },
            velocityThreshold = { with(density) { 100.dp.toPx() } },
            animationSpec = tween(300),
        ).apply {
            // 6
            updateAnchors(
                // 7
                DraggableAnchors {
                    DragAnchors.Start at 0f
                    DragAnchors.End at -250f
                }
            )
        }
    }

    val coroutineScope = rememberCoroutineScope()


    Box {
        Box(
            Modifier
                .matchParentSize()
                .padding(horizontal = 16.dp)
                .background(RedDelete, shape = RoundedCornerShape(15.dp)),
            contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_delete),
                contentDescription = "Delete",
                modifier = Modifier
                    .padding(end = 32.dp)
                    .size(38.dp)
                    .clickable {
                        onTapDeleteButton()
                    }
            )
        }
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        x = state
                            .requireOffset()
                            .roundToInt(),
                        y = 0
                    )
                }
                .anchoredDraggable(state, Orientation.Horizontal),

            ) {
            Box(
                Modifier
                    .padding(horizontal = 16.dp)
                    .clickable {
                        if (state.currentValue == DragAnchors.Start) {
                            onTapContent()
                        } else {
                            coroutineScope.launch {
                                state.animateTo(DragAnchors.Start)
                            }
                        }


                    }) {
                content()
            }
        }

    }
}