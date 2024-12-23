package com.house_shoreditch.app.util


interface Platform {
    val type: PlatformType
    val name: String
}

enum class PlatformType {
    Android, Ios, Web, Desktop
}

expect fun getPlatform(): Platform
