package com.house_shoreditch.app.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import osric.composeapp.generated.resources.Res
import osric.composeapp.generated.resources.*

object OsricAppBarComposables {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun OsricAppBar(
        title: String,
        subTitle: String? = null,
        modifier: Modifier = Modifier,
        backgroundColor: Color = MaterialTheme.colorScheme.primary,
        contentColor: Color = MaterialTheme.colorScheme.onSurface,
        actions: List<Action> = listOf(),
        overflowActions: List<Action>? = null,
        onUp: (() -> Unit)? = null,
    ) {
        TopAppBar(
            title = {
                Box(modifier = modifier.fillMaxHeight()) {
                    Column(modifier = modifier.align(Alignment.CenterStart)) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.headlineSmall,
                            maxLines = 1,
                            color = contentColor,
                        )
                        if (subTitle != null) {
                            Text(
                                text = subTitle,
                                style = MaterialTheme.typography.bodyMedium,
                                maxLines = 1,
                                color = contentColor,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                }
            },
            navigationIcon = {
                if (onUp != null) {
                    IconButton(onClick = { onUp() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            tint = contentColor,
                            contentDescription = stringResource(Res.string.up)
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = backgroundColor,
                titleContentColor = contentColor,
                navigationIconContentColor = contentColor,
                actionIconContentColor = contentColor,
            ),
            actions = { Actions(actions = actions, contentColor = contentColor, overflow = overflowActions) },
            modifier = modifier
        )
    }

    @Composable
    private fun Actions(
        actions: List<Action>,
        contentColor: Color = MaterialTheme.colorScheme.onSurface,
        overflow: List<Action>? = null,
    ) {
        actions.forEach { Action(it, contentColor) }
        overflow?.also {
            TopAppBarOverflowMenu(it, contentColor)
        }
    }

    @Composable
    private fun Action(
        action: Action,
        contentColor: Color = MaterialTheme.colorScheme.onSurface,
    ) {
        Icon(
            painter = painterResource(action.item.icon),
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier
                .clickable { action.action() }
                .size(48.dp)
                .padding(12.dp)
        )
    }
}

sealed class CuerMenuItem(
    val label: StringResource,
    val icon: DrawableResource,
) {
    object Home : CuerMenuItem(Res.string.menu_home, Res.drawable.home)
    object Images : CuerMenuItem(Res.string.menu_images, Res.drawable.photo_library)
    object Facilities : CuerMenuItem(Res.string.menu_facilities, Res.drawable.family_home)
    object Reviews : CuerMenuItem(Res.string.menu_reviews, Res.drawable.reviews)
    object Booking : CuerMenuItem(Res.string.menu_booking, Res.drawable.event_available)
    object Contact : CuerMenuItem(Res.string.menu_contact, Res.drawable.person)
}

data class Action(
    val item: CuerMenuItem,
    val action: () -> Unit,
)

@Composable
fun TopAppBarOverflowMenu(
    actions: List<Action> = listOf(),
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    var expanded by remember { mutableStateOf(false) }
    var menuOffset by remember { mutableStateOf(0.dp) }

    IconButton(onClick = { expanded = true },
        modifier = Modifier.onGloballyPositioned { coordinates -> menuOffset = coordinates.size.width.dp }
    ) {
        Icon(Icons.Filled.MoreVert, contentDescription = "Overflow Menu")
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        offset = DpOffset(menuOffset.value.dp, 0.dp),
        modifier = Modifier
    ) {
        actions.forEach { action ->
            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        painter = painterResource(action.item.icon),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                expanded = false
                                action.action()
                            }
                            .size(48.dp)
                            .padding(12.dp)
                    )
                },
                text = { Text(stringResource(action.item.label)) },
                onClick = {
                    expanded = false
                    action.action()
                },
            )
        }
    }
}

@Composable
fun CustomSnackbar(snackbarData: SnackbarData) {
    Snackbar(
        modifier = Modifier.background(MaterialTheme.colorScheme.error),
        containerColor = MaterialTheme.colorScheme.error,
        contentColor = MaterialTheme.colorScheme.onError,
        shape = MaterialTheme.shapes.medium,
        action = {
            TextButton(
                onClick = { snackbarData.dismiss() },
                elevation = ButtonDefaults.elevatedButtonElevation(4.dp, 2.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.error,
                    containerColor = MaterialTheme.colorScheme.onError,
                ),
                shape = MaterialTheme.shapes.small,
                modifier = Modifier,
            ) {
                Text(
                    text = snackbarData.visuals.actionLabel ?: stringResource(Res.string.dismiss),
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        },
    ) {
        Text(
            text = snackbarData.visuals.message,
            color = MaterialTheme.colorScheme.onError
        )
    }
}
