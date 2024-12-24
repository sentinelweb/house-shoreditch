@file:Suppress("MagicNumber")

package com.moonsift.app.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val WHITE = Color(0xFFFFFFFF)
val BLACK = Color(0xFF000000)
val BLACK_TSP = Color(0x88000000)
val LIGHT_GREY = Color(0xFFBBBBBB)
val GREY = Color(0xFF888888)
val DARK_GREY = Color(0xFF444444)

var surfaceColor: Color = WHITE
var onSurfaceColor: Color = BLACK

fun initColors(isDark: Boolean = false) {
    if (isDark) {
        surfaceColor = BLACK
        onSurfaceColor = WHITE
    }
}

@Suppress("unused")
val DarkColorScheme = darkColorScheme(
    primary = BLACK,
    primaryContainer = BLACK,
    onPrimary = WHITE,
    secondary = GREY,
    secondaryContainer = GREY,
    onSecondary = WHITE,
    tertiary = DARK_GREY,
    tertiaryContainer = DARK_GREY,
    onTertiary = WHITE,
    background = BLACK,
    onBackground = WHITE,
    surface = BLACK,
    onSurface = WHITE,
)

val LightColorScheme = lightColorScheme(
    primary = WHITE,
    primaryContainer = WHITE,
    onPrimary = BLACK,
    secondary = GREY,
    secondaryContainer = WHITE,
    onSecondary = WHITE,
    tertiary = LIGHT_GREY,
    tertiaryContainer = WHITE,
    onTertiary = BLACK,
    background = WHITE,
    onBackground = BLACK,
    surface = WHITE,
    onSurface = BLACK,

)

