package com.house_shoreditch.app

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.house_shoreditch.app.di.OsricModules
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    OsricModules.initKoin()
    ComposeViewport(document.body!!) {
        App()
    }
}
