package com.house_shoreditch.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform