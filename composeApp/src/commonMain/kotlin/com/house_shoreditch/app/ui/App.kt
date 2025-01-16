package com.house_shoreditch.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults.elevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.house_shoreditch.app.main.MainContract
import com.house_shoreditch.app.main.MainViewModel
import com.house_shoreditch.app.theme.components.Action
import com.house_shoreditch.app.theme.components.CuerMenuItem
import com.house_shoreditch.app.theme.components.OasisAppBarComposables
import com.house_shoreditch.app.util.PlatformType.Android
import com.house_shoreditch.app.util.PlatformType.Ios
import com.house_shoreditch.app.util.getPlatform
import com.moonsift.app.ui.theme.OasisTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import oasis.composeapp.generated.resources.Res
import oasis.composeapp.generated.resources.app_name
import oasis.composeapp.generated.resources.arrow_downward
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.min

private val MAX_PAGES = 6

private fun isMobile() = listOf(Android, Ios).contains(getPlatform().type)

@Composable
@Preview
fun App(viewModel: MainViewModel = koinViewModel()) {
//    val model = viewModel.model.collectAsState()
    val model = MainContract.ModelInitial
    var initialSize by remember { mutableStateOf(IntSize.Zero) }
    var page by remember { mutableStateOf(0) }
    val density = LocalDensity.current.density
    val verticalScrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val primaryActions = listOf(
        Action(CuerMenuItem.Images, {
            page = 1
            coroutineScope.scrollToPage(verticalScrollState, initialSize, density, page)
        }),

        Action(CuerMenuItem.Contact, {
            page = 5
            coroutineScope.scrollToPage(verticalScrollState, initialSize, density, page)
        }),
    )

    val secondaryActions = listOf(
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
    )

    val actions = if (isMobile()) {
        primaryActions
    } else {
        primaryActions
            .toMutableList()
            .apply { addAll(1, secondaryActions) }
            .toList()
    }

    val overflowActiovs = if (isMobile()) {
        secondaryActions
    } else {
        null
    }

    val pageJump = { p: Int ->
        coroutineScope.scrollToPage(verticalScrollState, initialSize, density, p)
    }
    OasisTheme {
        Scaffold(
            topBar = {
                OasisAppBarComposables.OasisAppBar(
                    title = stringResource(Res.string.app_name),
                    actions = actions,
                    overflowActions = overflowActiovs,
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
                            initialSize =
                                IntSize((it.size.width / density).toInt(), (it.size.height / density).toInt())
                        }
                    }
            ) {
                if (initialSize != IntSize.Zero) {
                    Intro.IntroScreen(size = initialSize, model = model, pageJump = pageJump)
                    Photos.PhotosView(size = initialSize, model = model)
                    Facilities.FacilitiesScreen(size = initialSize, model = model)
                    Reviews.ReviewsSection(size = initialSize, model = model)
                    Booking.BookingSection(size = initialSize, viewModel = viewModel)
                    Contact.ContactSection(size = initialSize, model = model, viewModel = viewModel)
//                    TestContent()
                }
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

