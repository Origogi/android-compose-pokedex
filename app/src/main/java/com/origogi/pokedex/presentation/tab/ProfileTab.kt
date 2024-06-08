package com.origogi.pokedex.presentation.tab

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.origogi.pokedex.R
import com.origogi.pokedex.presentation.theme.Black700
import com.origogi.pokedex.presentation.theme.Black80
import com.origogi.pokedex.presentation.theme.Black800
import com.origogi.pokedex.presentation.theme.PokedexTheme

@Composable
fun ProfileTab() {
    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier
                .height(67.dp)
                .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_anonymus),
                modifier = Modifier.size(44.dp),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(text = "Anonymous", style = MaterialTheme.typography.titleMedium)
        }
        Divider(
            color = Black80,
        )
        Column(
            Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 24.dp)) {
            Text(
                "Account Information",
                style = MaterialTheme.typography.headlineMedium,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Name",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Black800
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                "Anonymous",
                style = MaterialTheme.typography.bodyMedium ,
                color = Black700
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Email",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Black800
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                "anonymous@test.com",
                style = MaterialTheme.typography.bodyMedium ,
                color = Black700
            )

        }


    }
}

@Preview(name = "ProfileTab", showBackground = true)
@Composable
private fun PreviewProfileTab() {
    PokedexTheme {
        ProfileTab()
    }
}