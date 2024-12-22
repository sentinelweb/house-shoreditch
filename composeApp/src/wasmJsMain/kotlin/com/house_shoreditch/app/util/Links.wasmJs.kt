package com.house_shoreditch.app.util

import kotlinx.browser.window

actual class LinkLauncher {
    actual fun open(url: String) {
        window.open(url, target = "_blank")
    }
}
