package com.house_shoreditch.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.moonsift.app.ui.theme.OsricTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import osric.composeapp.generated.resources.Res
import osric.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    var initialSize by remember { mutableStateOf(IntSize.Zero) }
    OsricTheme {
        Scaffold { contentPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .onGloballyPositioned {
                        if (initialSize == IntSize.Zero) {
                            initialSize = it.parentCoordinates?.size ?: IntSize.Zero
                        }
                    }
                    .verticalScroll(rememberScrollState())
            ) {
                val height = initialSize.height / LocalDensity.current.density
                IntroScreen(height = height.dp)
                IntroScreen(height = height.dp)
            }
        }
    }
}

@Composable
private fun IntroScreen(
    height: Dp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
            .height(height)
            .padding(16.dp)
    ) {
        Text(
            "WELCOME",
            style = MaterialTheme.typography.displayLarge,

            )

        Text(
            "! Shoreditch Oasis - 3 Bed house",
            style = MaterialTheme.typography.headlineLarge,
        )
        Text(
            "Welcome to the oasis house in shoreditch, there is lots to see here",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
private fun WizardCode() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}
