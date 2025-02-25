package com.house_shoreditch.app.util

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import org.koin.java.KoinJavaComponent.getKoin

class AndroidPlatform(val context: Context) : Platform {
    override val type: PlatformType = PlatformType.Android
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val isSmsAvailable: Boolean = true
    override val isEmailAvailable: Boolean = true
    override val isWhatsappAvailable: Boolean
        get() = isPackageInstalled(context, "com.whatsapp")
    override val isGmailAvailable: Boolean
        get() = isPackageInstalled(context, "com.google.android.gm")
}

actual fun getPlatform(): Platform = getKoin().get<AndroidPlatform>()

fun isPackageInstalled(context: Context, packageName: String): Boolean {
    return try {
        context.packageManager.getPackageInfo(packageName, 0)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}
