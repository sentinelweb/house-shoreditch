package com.house_shoreditch.app.util

@Suppress("unused")
actual object DebugUtils {
    actual fun printErr(msg: String, err: Exception?) {
        println(msg)
        err?.printStackTrace()
    }
}
