package com.house_shoreditch.app.util

import android.os.Build

class AndroidPlatform : Platform {
    override val type: PlatformType = PlatformType.Android
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val isSmsAvailable: Boolean = true
    override val isEmailAvailable: Boolean = true
    override val isWhatsappAvailable: Boolean = true
}

actual fun getPlatform(): Platform = AndroidPlatform()
