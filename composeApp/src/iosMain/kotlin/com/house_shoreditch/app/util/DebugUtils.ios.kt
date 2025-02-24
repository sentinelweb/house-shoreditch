package com.house_shoreditch.app.util

import kotlinx.cinterop.ExperimentalForeignApi
import platform.posix.fputs
import platform.posix.stderr

@Suppress("unused")
actual object DebugUtils {
    @OptIn(ExperimentalForeignApi::class)
    actual fun printErr(msg: String, err: Exception?) {
        fputs("$msg\n", stderr)
        fputs("${err?.message}\n", stderr)
        err?.printStackTrace()
    }
}
