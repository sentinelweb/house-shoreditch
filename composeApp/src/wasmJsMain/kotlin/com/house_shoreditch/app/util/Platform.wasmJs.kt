package com.house_shoreditch.app.util

import kotlinx.browser.document
import kotlinx.browser.window

class WasmPlatform: Platform {
    override val type: PlatformType = PlatformType.Web
    override val name: String = "Web with Kotlin/Wasm"
    override val isSmsAvailable: Boolean = isMobile()
    override val isEmailAvailable: Boolean = true
}

fun isMobile():Boolean  {
    val userAgent = window.navigator.userAgent;
    return Regex("android|iphone|ipad|ipod", RegexOption.IGNORE_CASE).containsMatchIn(userAgent)
}

actual fun getPlatform(): Platform = WasmPlatform()
