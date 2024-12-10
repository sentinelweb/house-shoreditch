package com.house_shoreditch.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.moonsift.app.ui.theme.BLACK_TSP

object Intro {

    @Composable
    fun IntroScreen(
        size: IntSize,
        model: MainContract.Model,
    ) {

        Box(
            modifier = Modifier.fillMaxWidth()
                .height(size.height.dp)
        ) {
            AsyncImage(
                model = model.homeBackground,
                contentScale = ContentScale.Crop,
                contentDescription = "Description",
                modifier = Modifier.fillMaxSize()
            )
            Box(modifier = Modifier.fillMaxSize().background(BLACK_TSP))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            ) {
                Text(
                    "Shoreditch Oasis",
                    style = MaterialTheme.typography.displayLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )

                Text(
                    "A great place to stay in the heart of Shoreditch",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 32.dp)
                )
            }
        }
    }

}
