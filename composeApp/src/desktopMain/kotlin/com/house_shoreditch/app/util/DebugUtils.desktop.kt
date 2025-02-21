package com.house_shoreditch.app.util

actual object DebugUtils {
    actual fun printErr(msg: String, err: Exception?) {
        System.err.println(msg)
        err?.printStackTrace(System.err)
    }
}
