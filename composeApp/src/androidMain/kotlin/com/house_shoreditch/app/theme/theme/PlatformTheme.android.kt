package com.house_shoreditch.app.theme.theme

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.moonsift.app.ui.theme.surfaceColor

actual object PlatformTheme {
    actual @Composable fun sideEffects(isDark:Boolean) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                (view.context as? Activity)
                    ?.window
                    ?.apply {
                        navigationBarColor = surfaceColor.toArgb()
                        statusBarColor = surfaceColor.toArgb()
                        WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = !isDark
                    }
            }
        }
    }

}
