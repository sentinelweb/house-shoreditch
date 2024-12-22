package com.house_shoreditch.app

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.moonsift.app.ui.theme.BLACK_TSP
import org.jetbrains.compose.resources.painterResource
import osric.composeapp.generated.resources.Res
import osric.composeapp.generated.resources.garden_7_DSC_0291

object Intro {

    @Composable
    fun IntroScreen(
        size: IntSize,
        model: MainContract.Model,
    ) {

        Box(
            modifier = Modifier.fillMaxWidth()
                .height(size.height.dp)
                .background(BLACK_TSP)
        ) {
            ImageWithCustomFadeIn()
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

    @Composable
    fun ImageWithCustomFadeIn() {
        var isImageVisible by remember { mutableStateOf(false) }
        val alpha by animateFloatAsState(
            targetValue = if (isImageVisible) 1f else 0f,
            animationSpec = tween(1000)
        )

        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(200)
            isImageVisible = true
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(resource = Res.drawable.garden_7_DSC_0291),
                contentDescription = "Local resource image",
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(
                    color = BLACK_TSP,
                    blendMode = BlendMode.Multiply
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(alpha)
            )
        }
    }

}
