package com.house_shoreditch.app

import android.os.Build

class AndroidPlatform : Platform {
    override val type: PlatformType = PlatformType.Android
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
