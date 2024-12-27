package com.house_shoreditch.app.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.house_shoreditch.app.domain.EnquiryMessageDomain
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


actual class LinkLauncher : KoinComponent {

    val context: Context by inject()
    private val messageMapper: MessageMapper by inject()

    actual fun open(url: String) {
        Intent(Intent.ACTION_VIEW, Uri.parse(url))
            .apply { setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
            .apply { context.startActivity(this) }
    }

    actual fun encode(text: String): String = Uri.encode(text)

    actual fun mail(message: EnquiryMessageDomain) {
        val mailToUri = messageMapper.mapMailUrl(message)
        val data = Uri.parse(mailToUri)
        Intent(Intent.ACTION_VIEW)
            .setData(data)
            .apply { setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
            .apply { context.startActivity(this) }
    }

    actual fun gmail(message: EnquiryMessageDomain) {
        Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf(message.to))
            putExtra(Intent.EXTRA_SUBJECT, message.subject)
            putExtra(Intent.EXTRA_TEXT, message.message)
        }
            .apply { setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
            .apply { context.startActivity(this) }
    }

    actual fun sms(message: EnquiryMessageDomain) {
        Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("smsto:${Uri.encode(message.to)}")
            putExtra("sms_body", message.message)
        }.apply { setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
            .apply { context.startActivity(this) }

    }
}
