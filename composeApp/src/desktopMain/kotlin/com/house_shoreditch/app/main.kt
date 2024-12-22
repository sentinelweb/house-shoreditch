package com.house_shoreditch.app

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.house_shoreditch.app.di.OsricModules

fun main() = application {
    OsricModules.initKoin()
    val state = rememberWindowState(size = DpSize(1280.dp, 720.dp))
    Window(
        onCloseRequest = ::exitApplication,
        title = "Osric",
        state = state,
    ) {
        App()
    }
}
