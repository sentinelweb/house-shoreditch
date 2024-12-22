package com.house_shoreditch.app.util

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual class LinkLauncher {
    actual fun open(url: String) {
        val nsUrl = NSURL(string = url)
        if (UIApplication.sharedApplication.canOpenURL(nsUrl)) {
            UIApplication.sharedApplication.openURL(nsUrl)
        } else {
            println("Invalid URL or cannot open the URL.")
        }
    }
}
