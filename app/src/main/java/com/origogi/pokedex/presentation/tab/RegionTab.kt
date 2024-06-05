package com.origogi.pokedex.presentation.tab

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.origogi.pokedex.domain.model.RegionInfo
import com.origogi.pokedex.domain.model.backgroundImageUrl
import com.origogi.pokedex.presentation.theme.Black200
import com.origogi.pokedex.presentation.theme.Black80
import com.origogi.pokedex.presentation.theme.Black800
import com.origogi.pokedex.presentation.theme.PokedexTheme

@Composable
fun RegionTab(
    modifier: Modifier = Modifier
) {
    Box(modifier.fillMaxSize()) {
        Column {
            Box(
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Regions",
                    style = MaterialTheme.typography.titleMedium,
                    color = Black800
                )
            }
            Divider(
                color = Black80,
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(0.dp))
                for (region in RegionInfo.entries) {
                    RegionCard(region)
                }
                Spacer(modifier = Modifier.height(0.dp))
            }
        }
    }
}

@Composable
fun RegionCard(regionInfo: RegionInfo) {
    val painter = rememberAsyncImagePainter(regionInfo.backgroundImageUrl)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(115.dp)
            .clip(shape = RoundedCornerShape(15.dp))
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Black800.copy(alpha = 0.8f),
                            Black800.copy(alpha = 0.15f)
                        ),
                    )
                )
        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp)
        ) {
            Text(
                text = regionInfo.regionName,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
            )
            Text(
                text = "${regionInfo.regionId} º GENERATION",
                style = MaterialTheme.typography.labelSmall,
                color = Black200,
            )
        }

    }
}

@Preview(name = "RegionTab", showBackground = true)
@Composable
private fun PreviewRegionTab() {
    PokedexTheme {
        RegionTab()
    }
}