package com.origogi.pokedex.presentation.screen

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil.Coil
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.origogi.pokedex.domain.model.PokemonDetailInfo
import com.origogi.pokedex.domain.model.PokemonType
import com.origogi.pokedex.domain.model.mainType
import com.origogi.pokedex.presentation.components.PokemonTypeIcon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun PokemonDetailScreen(pokedexId: Int) {

}

@Composable
private fun Body(info: PokemonDetailInfo) {
    Column(modifier = Modifier.fillMaxWidth()) {
        PokemonImage(imageUrl = info.imageUrl, type = info.mainType)
        // PokemonName
        // PokemonType
        // PokemonHeight
        // PokemonWeight
    }
}

@Composable
private fun PokemonImage(imageUrl: String, type: PokemonType) {
    Box(Modifier.height(290.dp)) {
        PokemonTypeBackground(color = type.color)
        PokemonTypeIcon(
            type = type,
            modifier = Modifier
                .size(250.dp)
                .padding(top = 30.dp)

                .align(
                    alignment = Alignment.TopCenter
                )
        )
        PokemonGifImage(
            imageUrl = imageUrl,
            modifier = Modifier
                .align(
                    alignment = Alignment.BottomCenter
                )
//                .size(200.dp)
        )


        // Gif Image
    }
}

@Composable
private fun PokemonGifImage(modifier: Modifier = Modifier, imageUrl: String) {
    var targetImageSize by remember { mutableStateOf(DpSize(0.dp, 0.dp)) }
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    LaunchedEffect(imageUrl) {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                val request = ImageRequest.Builder(context)
                    .data(imageUrl)
                    .allowHardware(false)
                    .build()
                val result = (imageLoader.execute(request) as? SuccessResult)?.drawable
                result?.let {
                    val oWidth = it.intrinsicWidth.dp
                    val oHeight = it.intrinsicHeight.dp

                    val targetImageWidth = (oWidth * 3.5f).coerceAtMost(400f.dp)
                    val targetImageHeight = (oHeight * 3.5f).coerceAtMost(400f.dp)



                    withContext(Dispatchers.Main) {
                        targetImageSize = DpSize(targetImageWidth, targetImageHeight)
                        println(targetImageSize)
                    }
                }
            }
        }
    }

    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context)
                .data(data = imageUrl)
                .build(),
            imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = modifier
            .size(targetImageSize),
    )
}

@Composable
private fun PokemonTypeBackground(color: Color) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {


        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(0f, 200.dp.toPx())
            quadraticBezierTo(size.width / 2, 330.dp.toPx(), size.width, 200.dp.toPx())
            lineTo(size.width, 0f)
            close()
        }

        drawPath(
            path = path,
            color = color
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPokemonDetailScreen() {
    Body(
        info = PokemonDetailInfo(
            pokedexId = 6,
            name = "Bulbasaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/6.gif",
            types = listOf(
                PokemonType.Fire,
            ),
            height = 7,
            weight = 69
        )
    )
}