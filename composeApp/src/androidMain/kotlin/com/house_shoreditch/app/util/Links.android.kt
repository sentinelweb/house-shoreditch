package com.house_shoreditch.app.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual class LinkLauncher : KoinComponent {

    val context: Context by inject()

    actual fun open(url: String) {
        Intent(Intent.ACTION_VIEW, Uri.parse(url))
            .apply { setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
            .apply { context.startActivity(this) }
    }
}
