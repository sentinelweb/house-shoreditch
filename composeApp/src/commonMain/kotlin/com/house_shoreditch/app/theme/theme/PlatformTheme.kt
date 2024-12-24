package com.house_shoreditch.app.theme.theme

import androidx.compose.runtime.Composable

expect object PlatformTheme {
    @Composable
    fun sideEffects(isDark:Boolean): Unit
}
