package com.moonsift.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import osric.composeapp.generated.resources.*


lateinit var RobotoFamily: FontFamily
lateinit var GaramondFamily: FontFamily
lateinit var OsricTypography: Typography

@Composable
fun initFont() {
    RobotoFamily = FontFamily(
        Font(Res.font.Roboto_Bold, FontWeight.Bold),
//        Font(Res.font.Roboto_BoldItalic, FontWeight.Bold, FontStyle.Italic),
        Font(Res.font.Roboto_Regular, FontWeight.Normal),
//        Font(Res.font.Roboto_Italic, FontWeight.Normal, FontStyle.Italic),
        Font(Res.font.Roboto_Light, FontWeight.Light),
//        Font(Res.font.Roboto_LightItalic, FontWeight.Light, FontStyle.Italic),
        Font(Res.font.Roboto_Medium, FontWeight.Medium),
//        Font(Res.font.Roboto_MediumItalic, FontWeight.Medium, FontStyle.Italic),
        Font(Res.font.Roboto_Thin, FontWeight.Thin),
//        Font(Res.font.Roboto_ThinItalic, FontWeight.Thin, FontStyle.Italic),
    )

    GaramondFamily = FontFamily(
        Font(Res.font.EBGaramond_Bold, FontWeight.Bold),
        Font(Res.font.EBGaramond_Regular, FontWeight.Normal),
        Font(Res.font.EBGaramond_Medium, FontWeight.Medium),
        Font(Res.font.EBGaramond_SemiBold, FontWeight.SemiBold),
        Font(Res.font.EBGaramond_ExtraBold, FontWeight.ExtraBold),
    )

    OsricTypography = Typography(
        displayLarge = TextStyle(
            fontFamily = GaramondFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 64.sp,
        ),
        displayMedium = TextStyle(
            fontFamily = GaramondFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 40.sp,
        ),
        displaySmall = TextStyle(
            fontFamily = GaramondFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
        ),

        headlineLarge = TextStyle(
            fontFamily = RobotoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp,
        ),
        headlineMedium = TextStyle(
            fontFamily = RobotoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 28.sp,
        ),
        headlineSmall = TextStyle(
            fontFamily = RobotoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
        ),

        titleLarge = TextStyle(
            fontFamily = RobotoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
        ),

        titleMedium = TextStyle(
            fontFamily = RobotoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
        ),

        titleSmall = TextStyle(
            fontFamily = RobotoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
        ),

        bodyLarge = TextStyle(
            fontFamily = RobotoFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
        ),

        bodyMedium = TextStyle(
            fontFamily = RobotoFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        ),

        bodySmall = TextStyle(
            fontFamily = RobotoFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
        ),

        labelLarge = TextStyle(
            fontFamily = RobotoFamily,
            fontWeight = FontWeight.Light,
            fontSize = 18.sp,
        ),

        labelMedium = TextStyle(
            fontFamily = RobotoFamily,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
        ),
        labelSmall = TextStyle(
            fontFamily = RobotoFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
        ),
    )
}
