package com.house_shoreditch.app.util

class JVMPlatform: Platform {
    override val type: PlatformType = PlatformType.Desktop
    override val name: String = "Java ${System.getProperty("java.version")}"
    override val isSmsAvailable: Boolean = false
    override val isEmailAvailable: Boolean = true
}

actual fun getPlatform(): Platform = JVMPlatform()
