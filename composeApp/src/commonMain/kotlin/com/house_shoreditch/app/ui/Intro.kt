package com.house_shoreditch.app.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.house_shoreditch.app.domain.PaymentMethod
import com.house_shoreditch.app.main.MainContract
import com.house_shoreditch.app.theme.theme.BLACK_TSP
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import oasis.composeapp.generated.resources.Res
import oasis.composeapp.generated.resources.garden_7_DSC_0291

object Intro {

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    fun IntroScreen(
        size: IntSize,
        model: MainContract.Model,
        pageJump: (Int) -> Unit,
    ) {

        Box(
            modifier = Modifier.fillMaxWidth()
                .height(size.height.dp)
                .background(BLACK_TSP)
        ) {
            ImageWithCustomFadeIn(model)
            Title()
            PaymentDisplay(pageJump)
        }
    }

    @Composable
    @Suppress("LongMethod", "MagicNumber")
    private fun BoxScope.PaymentDisplay(pageJump: (Int) -> Unit) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.Companion.align(Alignment.BottomStart)
                .padding(8.dp)
        ) {
            val coroutineScope = rememberCoroutineScope()
            val rotation = remember { Animatable(-90f) }
            var paymentMethodIndex by remember { mutableStateOf(0) }
            val drawable by remember { derivedStateOf { PaymentMethod.entries[paymentMethodIndex].drawable } }

            LaunchedEffect(Unit) {
                coroutineScope.launch {
                    while (true) {
                        rotation.animateTo(
                            targetValue = 90f,
                            animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
                        )
                        rotation.snapTo(-90f)
                        paymentMethodIndex = (paymentMethodIndex + 1) % PaymentMethod.entries.size
                    }
                }
            }

            Icon(
                painter = painterResource(drawable),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(48.dp)
                    .graphicsLayer {
                        this.rotationY = rotation.value
                    }
                    .clickable { pageJump(4) }
            )

            Text(
                "We accept",
                color = Color.White,
                style = MaterialTheme.typography.displaySmall,
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }

    @Composable
    @Suppress( "MagicNumber")
    private fun BoxScope.Title() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.Companion
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
                text = "A great place to stay in the heart of Shoreditch",
                style = MaterialTheme.typography.labelLarge,
                fontSize = 24.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }

    @Composable
    @Suppress( "MagicNumber")
    fun ImageWithCustomFadeIn(
        model: MainContract.Model
    ) {
        var isImageVisible by remember { mutableStateOf(false) }
        var currentImageIndex by remember { mutableStateOf(0) }
        var currentImagePath by remember { mutableStateOf(model.homeBackgroundUris.get(currentImageIndex)) }

        LaunchedEffect(Unit) {
            delay(200)
            isImageVisible = true
        }

        LaunchedEffect(Unit) {
            while (true) {
                delay(5000)
                currentImageIndex = (currentImageIndex + 1) % model.homeBackgroundUris.size
                currentImagePath = model.homeBackgroundUris.get(currentImageIndex)
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(resource = Res.drawable.garden_7_DSC_0291),
                contentDescription = "Initial intro image",
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(
                    color = BLACK_TSP,
                    blendMode = BlendMode.Multiply
                ),
                modifier = Modifier
                    .fillMaxSize()
            )
            Crossfade(
                targetState = currentImagePath,
                animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
            ) { photoPath ->
                AsyncImage(
                    model = photoPath,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Description",
                    colorFilter = ColorFilter.tint(
                        color = BLACK_TSP,
                        blendMode = BlendMode.Multiply
                    ),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize()
                )
            }
        }
    }
}
