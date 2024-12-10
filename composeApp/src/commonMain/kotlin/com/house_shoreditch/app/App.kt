package com.house_shoreditch.app

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.FloatingActionButtonDefaults.elevation
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.house_shoreditch.app.theme.components.Action
import com.house_shoreditch.app.theme.components.CuerMenuItem
import com.house_shoreditch.app.theme.components.OsricAppBarComposables
import com.moonsift.app.ui.theme.OsricTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import osric.composeapp.generated.resources.Res
import osric.composeapp.generated.resources.arrow_downward
import kotlin.math.min

private val MAX_PAGES = 6

@Composable
@Preview
fun App(viewModel: MainVViewModel = MainVViewModel()) {
    val model = viewModel.model.collectAsState()
    var initialSize by remember { mutableStateOf(IntSize.Zero) }
    var page by remember { mutableStateOf(0) }
    val density = LocalDensity.current.density
    val verticalScrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    OsricTheme {
        Scaffold(
            topBar = {
                OsricAppBarComposables.OsricAppBar(
                    title = "Shoreditch Oasis",
                    actions = listOf(
                        Action(CuerMenuItem.Images, {
                            page = 1
                            coroutineScope.scrollToPage(verticalScrollState, initialSize, density, page)
                        }),
                        Action(CuerMenuItem.Facilities, {
                            page = 2
                            coroutineScope.scrollToPage(verticalScrollState, initialSize, density, page)
                        }),
                        Action(CuerMenuItem.Reviews, {
                            page = 3
                            coroutineScope.scrollToPage(verticalScrollState, initialSize, density, page)
                        }),
                        Action(CuerMenuItem.Booking, {
                            page = 4
                            coroutineScope.scrollToPage(verticalScrollState, initialSize, density, page)
                        }),
                        Action(CuerMenuItem.Contact, {
                            page = 5
                            coroutineScope.scrollToPage(verticalScrollState, initialSize, density, page)
                        }),
                    ),
                    onUp = {
                        page = 0
                        coroutineScope.scrollToPage(verticalScrollState, initialSize, density, page)
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        page = min(page + 1, MAX_PAGES)
                        coroutineScope.scrollToPage(verticalScrollState, initialSize, density, page)
                    },
                    modifier = Modifier,
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary,
                    elevation = elevation(4.dp, 2.dp, 0.dp),
                ) {
                    Image(
                        painterResource(Res.drawable.arrow_downward),
                        null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onTertiary),
                    )
                }
            }
        ) { contentPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .verticalScroll(verticalScrollState)
                    .onGloballyPositioned {
                        if (initialSize == IntSize.Zero) {
                            initialSize = IntSize((it.size.width / density).toInt(), (it.size.height / density).toInt())
                        }
                    }
            ) {
                IntroScreen(height = initialSize.height.dp)
                Photos.PhotosView(size = initialSize, model.value)
                Facilities(height = initialSize.height.dp)
                Reviews(height = initialSize.height.dp)
                Booking(height = initialSize.height.dp)
                Contact(height = initialSize.height.dp)
            }
        }
    }
}

private fun CoroutineScope.scrollToPage(
    verticalScrollState: ScrollState,
    initialSize: IntSize,
    density: Float,
    page: Int
) {
    launch {
        verticalScrollState.animateScrollTo((initialSize.height * density * page).toInt())
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
private fun Facilities(
    height: Dp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
            .height(height)
            .padding(16.dp)
    ) {
        Text(
            "Facilities",
            style = MaterialTheme.typography.displayLarge,
        )
    }
}

@Composable
private fun Reviews(
    height: Dp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
            .height(height)
            .padding(16.dp)
    ) {
        Text(
            "Reviews",
            style = MaterialTheme.typography.displayLarge,
        )
    }
}

@Composable
private fun Booking(
    height: Dp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
            .height(height)
            .padding(16.dp)
    ) {
        Text(
            "Booking",
            style = MaterialTheme.typography.displayLarge,
        )
    }
}

@Composable
private fun Contact(
    height: Dp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
            .height(height)
            .padding(16.dp)
    ) {
        Text(
            "Contact",
            style = MaterialTheme.typography.displayLarge,
        )
    }
}
