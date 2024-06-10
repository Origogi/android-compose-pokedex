package com.origogi.pokedex.presentation.screen

import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.origogi.pokedex.R
import com.origogi.pokedex.domain.model.PokemonCardInfo
import com.origogi.pokedex.domain.model.PokemonDetailInfo
import com.origogi.pokedex.domain.model.PokemonEvolutionChainInfo
import com.origogi.pokedex.domain.model.PokemonType
import com.origogi.pokedex.domain.model.mainType
import com.origogi.pokedex.extenstion.PokedexIdString
import com.origogi.pokedex.presentation.components.LikeButton
import com.origogi.pokedex.presentation.components.PokemonEvolutionInfoView
import com.origogi.pokedex.presentation.components.PokemonGenderRatioView
import com.origogi.pokedex.presentation.components.PokemonStatusGroup
import com.origogi.pokedex.presentation.components.PokemonTypeIcon
import com.origogi.pokedex.presentation.router.LocalNavScreenController
import com.origogi.pokedex.presentation.theme.PokedexTheme
import com.origogi.pokedex.presentation.theme.isDark
import com.origogi.pokedex.presentation.viewmodel.PokemonDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.ceil

private const val TYPE_CHIP_HEIGHT = 36

@Composable
fun PokemonDetailScreen(viewModel: PokemonDetailViewModel = hiltViewModel()) {

    val pokemonInfo by viewModel.pokemonInfo.collectAsState()

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Crossfade(targetState = pokemonInfo, label = "", animationSpec = tween(1000)) { info ->
            if (info == null) {
                PokemonDetailPlaceholder()
            } else {
                Body(info, LocalNavScreenController.current)
            }
        }
    }

}

@Composable
private fun Body(info: PokemonDetailInfo, navController: NavController = rememberNavController()) {

    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding())
        ) {


            item {
                Box {
                    PokemonImage(imageUrl = info.animatedImageUrl, type = info.mainType)
                    AppBar(
                        modifier = Modifier
                            .padding(top = it.calculateTopPadding()),
                        pokedexId = info.pokedexId,
                        navController
                    )
                }
            }

            item {
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

                    Divider(color = Color.Black.copy(alpha = 0.05f))
                    Spacer(modifier = Modifier.height(20.dp))

                    PokemonStatusGroup(info)

                    Spacer(modifier = Modifier.height(20.dp))

                    PokemonGenderRatioView(ratio = info.genderRatio)

                    Spacer(modifier = Modifier.height(40.dp))

                    PokemonWeaknessTypesGrid(types = info.weaknessTypes)

                    Spacer(modifier = Modifier.height(40.dp))
                    if (info.evolutionChainInfo != null) {
                        PokemonEvolutionInfoView(info.evolutionChainInfo)
                        Spacer(modifier = Modifier.height(26.dp))
                    }
                }
            }
        }
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
private fun PokemonTypeChip(modifier: Modifier = Modifier, type: PokemonType) {
    Box(
        modifier = modifier
            .background(
                type.color,
                shape = RoundedCornerShape(67.dp)
            )
            .height(TYPE_CHIP_HEIGHT.dp)
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
fun PokemonWeaknessTypesGrid(types: List<PokemonType>) {
    val gridColumnCount = ceil((types.size / 2.0))
    val itemsTotalSpacing = (12 * (gridColumnCount - 1))

    val gridHeight = (TYPE_CHIP_HEIGHT * gridColumnCount) + itemsTotalSpacing

    Column {
        Text(
            text = "Weakness",
            style = MaterialTheme.typography.labelLarge,

            )

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            modifier = Modifier.height(gridHeight.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(types) { type ->
                PokemonTypeChip(type = type)
            }
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
private fun PokemonTypeBackground(modifier: Modifier = Modifier, color: Color) {
    Canvas(
        modifier
            .fillMaxSize()
            .background(Color.Transparent)
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

@Composable
fun PokemonDetailPlaceholder() {

    val transition = rememberInfiniteTransition(label = "")
    val shimmerTranslateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Column(
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
                        shimmerTranslateAnim.value - 200f,
                        shimmerTranslateAnim.value - 200f
                    ),
                    end = Offset(shimmerTranslateAnim.value, shimmerTranslateAnim.value)
                )

                // Destination
                drawContent()

                // Source
                drawRect(
                    brush = shimmerBrush,
                    blendMode = androidx.compose.ui.graphics.BlendMode.SrcIn
                )

                restoreToCount(checkPoint)

            }
        },

        ) {
        Box(modifier = Modifier.height(290.dp)) {
            PokemonTypeBackground(
                modifier = Modifier,
                color = Color.LightGray
            )
        }

        Column(Modifier.padding(16.dp)) {
            Box(
                Modifier
                    .size(width = 200.dp, height = 40.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(6.dp))

            Box(
                Modifier
                    .size(width = 70.dp, height = 22.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row {
                Box(
                    Modifier
                        .size(width = 110.dp, height = 36.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(67.dp))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    Modifier
                        .size(width = 110.dp, height = 36.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(67.dp))
                )

            }
            Spacer(modifier = Modifier.height(24.dp))

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
            )


        }


    }
}

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    pokedexId: Int,
    navController: NavController = rememberNavController()
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(38.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_arrow_left),
            modifier = Modifier
                .size(38.dp)
                .clickable {
                    navController.popBackStack()
                },
            contentDescription = ""
        )


        LikeButton(pokedexId = pokedexId)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPokemonDetailPlaceholder() {
    PokedexTheme {
        PokemonDetailPlaceholder()
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
                desc = "It is a small quadruped Pokémon that has blue fur with a white face and paws. It has rounded ears with pink insides, big blue eyes, and a small black nose. Its paws each have three toes, with the outer two being larger than the inner one. It also has a long, curled tail.",
                abilities = listOf("Blaze", "Solar Power"),
                category = "Lizard Pokémon",
                genderRatio = 0.5,
                weaknessTypes = listOf(PokemonType.Water, PokemonType.Electric),
                evolutionChainInfo = PokemonEvolutionChainInfo(
                    cardInfo = PokemonCardInfo(
                        pokedexId = 1,
                        name = "Bulbasaur",
                        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/1.png",
                        types = listOf(
                            PokemonType.Grass,
                            PokemonType.Poison
                        ),
                    ),
                    next = PokemonEvolutionChainInfo(
                        cardInfo = PokemonCardInfo(
                            pokedexId = 2,
                            name = "Ivysaur",
                            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/2.png",
                            types = listOf(
                                PokemonType.Grass,
                                PokemonType.Poison
                            ),
                        ),
                        next = PokemonEvolutionChainInfo(
                            cardInfo = PokemonCardInfo(
                                pokedexId = 3,
                                name = "Venusaur",
                                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/3.png",
                                types = listOf(
                                    PokemonType.Grass,
                                    PokemonType.Poison
                                ),
                            ),
                            next = null
                        )
                    )
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWeaknessTypeChipsGrid() {
    PokedexTheme {
        PokemonWeaknessTypesGrid(
            types = listOf(
                PokemonType.Fire,
                PokemonType.Water,
                PokemonType.Electric,
                PokemonType.Grass,
                PokemonType.Poison,
                PokemonType.Fighting,
                PokemonType.Ground,
                PokemonType.Flying,
                PokemonType.Psychic,
                PokemonType.Bug,
                PokemonType.Rock,
                PokemonType.Ghost,
                PokemonType.Ice,
                PokemonType.Dragon,
                PokemonType.Dark,
                PokemonType.Steel,
                PokemonType.Fairy
            )
        )
    }
}

