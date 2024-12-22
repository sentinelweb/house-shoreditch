package com.house_shoreditch.app.util

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val type: PlatformType = PlatformType.Ios
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()
