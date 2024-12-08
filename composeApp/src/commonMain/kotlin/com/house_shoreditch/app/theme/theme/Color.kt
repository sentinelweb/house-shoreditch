@file:Suppress("MagicNumber")

package com.moonsift.app.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val WHITE = Color(0xFFFFFFFF)
val BLACK = Color(0xFF000000)

@Suppress("unused")
val DarkColorScheme = darkColorScheme(
    primary = BLACK,
    secondary = BLACK,
    tertiary = BLACK
)

val LightColorScheme = lightColorScheme(
    primary = WHITE,
    secondary = BLACK,
    background = WHITE,
    surface = WHITE,
    onSurface = BLACK,
    onBackground = BLACK,
    onPrimary = BLACK,
    onSecondary = WHITE,
    onTertiary = BLACK,
)

