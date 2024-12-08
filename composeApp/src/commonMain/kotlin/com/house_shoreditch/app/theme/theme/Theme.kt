@file:Suppress("FunctionNaming")

package com.moonsift.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun OsricTheme(
    @Suppress("unused") darkTheme: Boolean = isSystemInDarkTheme(),
    @Suppress("unused") dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    initFont()
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = OsricTypography,
        content = content
    )
}
