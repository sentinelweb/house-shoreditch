package com.house_shoreditch.app.util

expect object DebugUtils {
    fun printErr(msg: String, err: Exception? = null)
}
