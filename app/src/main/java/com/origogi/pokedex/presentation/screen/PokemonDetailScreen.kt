package com.origogi.pokedex.presentation.screen

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.origogi.pokedex.domain.model.PokemonDetailInfo
import com.origogi.pokedex.domain.model.PokemonInfo
import com.origogi.pokedex.domain.model.PokemonType
import com.origogi.pokedex.domain.model.mainType
import com.origogi.pokedex.extenstion.PokedexIdString
import com.origogi.pokedex.presentation.components.PokemonTypeIcon
import com.origogi.pokedex.presentation.theme.PokedexTheme
import com.origogi.pokedex.presentation.theme.isDark
import com.origogi.pokedex.presentation.viewmodel.PokemonDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun PokemonDetailScreen(viewModel: PokemonDetailViewModel = hiltViewModel()) {

    val pokemonInfo by viewModel.pokemonInfo.collectAsState()

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
        if (pokemonInfo != null) {
            Body(info = pokemonInfo!!)
        }
    }

}

@Composable
private fun Body(info: PokemonDetailInfo) {
    Column{
        PokemonImage(imageUrl = info.animatedImageUrl, type = info.mainType)

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            Text(
                text = info.name,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = info.pokedexId.PokedexIdString(),
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            PokemonTypesRow(types = info.types)

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = info.desc,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black.copy(alpha = 0.7f)
            )


            Spacer(modifier = Modifier.height(20.dp))


        }

        // PokemonHeight
        // PokemonWeight
    }
}

@Composable
fun PokemonTypesRow(types: List<PokemonType>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(7.dp),
    ) {
        types.forEach {
            PokemonTypeChip(type = it)
        }
    }
}

@Composable
private fun PokemonTypeChip(type: PokemonType) {
    Box(
        modifier = Modifier
            .background(
                type.color,
                shape = RoundedCornerShape(67.dp)
            )
            .padding(horizontal = 16.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .size(28.dp)
                    .background(Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = type.iconAssetId),
                    contentDescription = "",
                    modifier = Modifier.size(18.dp),
                    colorFilter = ColorFilter.tint(type.color)
                )

            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = type.label,
                style = MaterialTheme.typography.labelMedium,
                color = if (type.color.isDark()) Color.Black else Color.White,
            )
        }
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

                    val targetImageWidth = (oWidth * 2.5f).coerceAtMost(400f.dp)
                    val targetImageHeight = (oHeight * 2.5f).coerceAtMost(400f.dp)

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
    PokedexTheme {
        Body(
            info = PokemonDetailInfo(
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
                desc = "It is a small quadruped Pokémon that has blue fur with a white face and paws. It has rounded ears with pink insides, big blue eyes, and a small black nose. Its paws each have three toes, with the outer two being larger than the inner one. It also has a long, curled tail."
            )
        )
    }

}