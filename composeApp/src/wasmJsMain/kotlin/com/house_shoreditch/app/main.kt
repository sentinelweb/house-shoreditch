package com.house_shoreditch.app

import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.window.CanvasBasedWindow
import com.house_shoreditch.app.di.OsricModules
import com.moonsift.app.ui.theme.GaramondFamily
import kotlinx.browser.document
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import osric.composeapp.generated.resources.Res
import osric.composeapp.generated.resources.app_name

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    OsricModules.initKoin()

    @OptIn(ExperimentalResourceApi::class)
    @Suppress("MaxLineLength")
    CanvasBasedWindow("Loading - Oasis Shoreditch", canvasElementId = "homepageCanvas") {
        // font loading code borrowed from here:
        // https://git.chrishatton.org/chrishatton.org/homepage/-/blob/master/client/src/wasmJsMain/kotlin/org/chrishatton/homepage/presentation/view/Main.kt?ref_type=heads
        var isFontsPreloaded by remember { mutableStateOf(false) }
        val fontResolver = LocalFontFamilyResolver.current
        LaunchedEffect(Unit) {
            GaramondFamily = FontFamily(listOf(
                Font("EBGaramond_Bold", Res.readBytes("font/EBGaramond_Bold.ttf"), FontWeight.Bold),
                Font("EBGaramond_ExtraBold", Res.readBytes("font/EBGaramond_ExtraBold.ttf"), FontWeight.ExtraBold),
                Font("EBGaramond_SemiBold", Res.readBytes("font/EBGaramond_SemiBold.ttf"), FontWeight.SemiBold),
                Font("EBGaramond_Medium", Res.readBytes("font/EBGaramond_Medium.ttf"), FontWeight.Medium),
                Font("EBGaramond_Regular", Res.readBytes("font/EBGaramond_Regular.ttf"), FontWeight.Normal),
            ))

            fontResolver.preload(GaramondFamily)
            isFontsPreloaded = true
        }
        document.title = stringResource(Res.string.app_name)
        if (isFontsPreloaded) {
            App()
        }
    }
}
