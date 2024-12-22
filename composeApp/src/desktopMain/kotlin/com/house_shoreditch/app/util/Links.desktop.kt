package com.house_shoreditch.app.util

import java.awt.Desktop
import java.net.URI

actual class LinkLauncher {
    actual fun open(url: String) {
        if (Desktop.isDesktopSupported()) {
            val desktop = Desktop.getDesktop()
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(URI(url))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                println("BROWSE action is not supported on this system.")
            }
        } else {
            println("Desktop is not supported on this system.")
        }
    }
}
