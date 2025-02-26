package com.house_shoreditch.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import com.house_shoreditch.app.main.MainContract.Companion.ModelStatic
import com.house_shoreditch.app.main.MainViewModel
import com.house_shoreditch.app.theme.components.Action
import com.house_shoreditch.app.theme.components.CuerMenuItem
import com.house_shoreditch.app.theme.components.OasisAppBarComposables
import com.house_shoreditch.app.theme.theme.OasisTheme
import com.house_shoreditch.app.util.PlatformType.Android
import com.house_shoreditch.app.util.PlatformType.Ios
import com.house_shoreditch.app.util.getPlatform
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import oasis.composeapp.generated.resources.Res
import oasis.composeapp.generated.resources.app_name
import oasis.composeapp.generated.resources.arrow_downward
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.min

private const val MAX_PAGES = 6

private fun isMobile() = listOf(Android, Ios).contains(getPlatform().type)

private const val PHOTOS_PAGE = 1
private const val FACILITIES_PAGE = 2
private const val REVIEWS_PAGE = 3
const val BOOKING_PAGE = 4
private const val CONTACTS_PAGE = 5

@Composable
@Suppress("MagicNumber")
fun App(viewModel: MainViewModel = koinViewModel()) {
    var initialSize by remember { mutableStateOf(IntSize.Zero) }
    var page by remember { mutableStateOf(0) }
    val density = LocalDensity.current.density
    val verticalScrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    val actions = rememberActions(
        coroutineScope,
        verticalScrollState,
        initialSize,
        density,
        onPrimaryAction = { page = it },
        onSecondaryAction = { page = it })
    val overflowActions = getOverflowActions(actions)

    val pageJump = { p: Int ->
        coroutineScope.scrollToPage(verticalScrollState, initialSize, density, p)
    }

    OasisTheme {
        Scaffold(
            topBar = {
                AppBar(
                    actions = actions,
                    overflowActions = overflowActions,
                    onUp = {
                        page = 0
                        coroutineScope.scrollToPage(verticalScrollState, initialSize, density, page)
                    }
                )
            },
            floatingActionButton = {
                PageScrollFab(
                    onNextPage = {
                        page = min(page + 1, MAX_PAGES)
                        coroutineScope.scrollToPage(verticalScrollState, initialSize, density, page)
                    }
                )
            }
        ) { contentPadding ->
            AppContent(
                contentPadding = contentPadding,
                verticalScrollState = verticalScrollState,
                initialSize = initialSize,
                updateInitialSize = { initialSize = it },
                pageJump = pageJump,
                viewModel = viewModel
            )
        }
    }
}

@Composable
private fun rememberActions(
    coroutineScope: CoroutineScope,
    verticalScrollState: ScrollState,
    initialSize: IntSize,
    density: Float,
    onPrimaryAction: (Int) -> Unit,
    onSecondaryAction: (Int) -> Unit
): List<Action> {
    val primaryActions = listOf(
        Action(
            CuerMenuItem.Images,
            {
                onPrimaryAction(PHOTOS_PAGE);
                coroutineScope.scrollToPage(verticalScrollState, initialSize, density, PHOTOS_PAGE)
            }),
        Action(
            CuerMenuItem.Contact,
            {
                onPrimaryAction(CONTACTS_PAGE)
                coroutineScope.scrollToPage(verticalScrollState, initialSize, density, CONTACTS_PAGE)
            }),
    )
    val secondaryActions = listOf(
        Action(
            CuerMenuItem.Facilities,
            {
                onSecondaryAction(FACILITIES_PAGE)
                coroutineScope.scrollToPage(verticalScrollState, initialSize, density, FACILITIES_PAGE)
            }),
        Action(
            CuerMenuItem.Reviews,
            {
                onSecondaryAction(REVIEWS_PAGE);
                coroutineScope.scrollToPage(verticalScrollState, initialSize, density, REVIEWS_PAGE)
            }),
        Action(
            CuerMenuItem.Booking,
            {
                onSecondaryAction(BOOKING_PAGE)
                coroutineScope.scrollToPage(verticalScrollState, initialSize, density, BOOKING_PAGE)
            }),
    )
    return if (isMobile()) primaryActions else primaryActions + secondaryActions
}

@Composable
private fun getOverflowActions(actions: List<Action>): List<Action>? {
    return if (isMobile()) actions.drop(2) else null
}

@Composable
private fun AppBar(actions: List<Action>, overflowActions: List<Action>?, onUp: () -> Unit) {
    OasisAppBarComposables.OasisAppBar(
        title = stringResource(Res.string.app_name),
        actions = actions,
        overflowActions = overflowActions,
        onUp = onUp
    )
}

@Composable
private fun PageScrollFab(onNextPage: () -> Unit) {
    FloatingActionButton(
        onClick = onNextPage,
        modifier = Modifier,
        containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.onTertiary,
        elevation = elevation(BOOKING_PAGE.dp, 2.dp, 0.dp),
    ) {
        Image(
            painterResource(Res.drawable.arrow_downward),
            null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onTertiary),
        )
    }
}

@Composable
private fun AppContent(
    contentPadding: PaddingValues,
    verticalScrollState: ScrollState,
    initialSize: IntSize,
    updateInitialSize: (IntSize) -> Unit,
    pageJump: (Int) -> Unit,
    viewModel: MainViewModel
) {
    val density = LocalDensity.current.density
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .verticalScroll(verticalScrollState)
            .onGloballyPositioned {
                if (initialSize == IntSize.Zero) {
                    updateInitialSize(
                        IntSize(
                            (it.size.width / density).toInt(),
                            (it.size.height / density).toInt()
                        )
                    )
                }
            }
    ) {
        if (initialSize != IntSize.Zero) {
            Intro.IntroScreen(size = initialSize, model = ModelStatic, pageJump = pageJump)
            Photos.PhotosView(size = initialSize, model = ModelStatic)
            Facilities.FacilitiesScreen(size = initialSize, model = ModelStatic)
            Reviews.ReviewsSection(size = initialSize, model = ModelStatic)
            Booking.BookingSection(size = initialSize, viewModel = viewModel)
            Contact.ContactSection(size = initialSize, model = ModelStatic, viewModel = viewModel)
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

