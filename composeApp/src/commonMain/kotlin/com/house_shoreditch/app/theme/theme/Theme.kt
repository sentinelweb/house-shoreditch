@file:Suppress("FunctionNaming")

package com.house_shoreditch.app.theme.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.house_shoreditch.app.theme.components.initButtonsColors

@Composable
fun OasisTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    initFont()
    initColors(darkTheme)
    initButtonsColors()
    PlatformTheme.sideEffects(darkTheme)
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = OasisTypography,
        content = content
    )
}
