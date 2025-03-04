package com.house_shoreditch.app.util


interface Platform {
    val type: PlatformType
    val name: String
    val isSmsAvailable: Boolean
    val isEmailAvailable: Boolean
    val isWhatsappAvailable: Boolean
    val isGmailAvailable: Boolean
}

enum class PlatformType {
    Android, Ios, Web, Desktop
}

expect fun getPlatform(): Platform
