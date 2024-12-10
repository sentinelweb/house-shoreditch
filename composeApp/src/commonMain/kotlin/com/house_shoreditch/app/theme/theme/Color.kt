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

@Suppress("unused")
val DarkColorScheme = darkColorScheme(
    primary = BLACK,
    onPrimary = WHITE,
    secondary = BLACK,
    onSecondary = WHITE,
    tertiary = DARK_GREY,
    onTertiary = WHITE,
    background = BLACK,
    onBackground = WHITE,
    surface = WHITE,
    onSurface = BLACK,
)

val LightColorScheme = lightColorScheme(
    primary = WHITE,
    onPrimary = BLACK,
    secondary = BLACK,
    onSecondary = WHITE,
    tertiary = LIGHT_GREY,
    onTertiary = BLACK,
    background = WHITE,
    onBackground = BLACK,
    surface = WHITE,
    onSurface = BLACK,
)

