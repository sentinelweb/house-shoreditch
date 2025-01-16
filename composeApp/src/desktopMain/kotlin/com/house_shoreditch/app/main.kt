package com.house_shoreditch.app

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.house_shoreditch.app.di.OasisModules
import com.house_shoreditch.app.ui.App
import oasis.composeapp.generated.resources.Res
import oasis.composeapp.generated.resources.app_name
import oasis.composeapp.generated.resources.home_512
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    OasisModules.initKoin()
    val state = rememberWindowState(size = DpSize(1472.dp, 828.dp))
    val icon = painterResource(Res.drawable.home_512)
    Tray(
        icon = icon,
        menu = {
            Item("Quit App", onClick = ::exitApplication)
        }
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
        state = state,
        resizable = false,
        icon = icon
    ) {
        App()
    }
}
