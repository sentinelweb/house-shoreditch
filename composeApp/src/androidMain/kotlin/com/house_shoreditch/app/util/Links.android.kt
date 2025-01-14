package com.house_shoreditch.app.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.house_shoreditch.app.domain.EnquiryMessageDomain
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


actual class LinkLauncher : KoinComponent {

    val context: Context by inject()
    private val messageMapper: MessageMapper by inject()

    actual fun open(url: String) {
        kotlin.runCatching {
            Intent(Intent.ACTION_VIEW, Uri.parse(url))
                .apply { setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
                .apply { context.startActivity(this) }
        }.onFailure { showErrorToast("Broswer") }
    }

    actual fun mail(message: EnquiryMessageDomain) {
        kotlin.runCatching {
            val mailToUri = messageMapper.mapMailUrl(message)
            val data = Uri.parse(mailToUri)
            Intent(Intent.ACTION_VIEW)
                .setData(data)
                .apply { setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
                .apply { context.startActivity(this) }
        }.onFailure { showErrorToast("E-mail") }
    }

    actual fun gmail(message: EnquiryMessageDomain) {
        kotlin.runCatching {
            Intent(Intent.ACTION_SEND).apply {
                type = "message/rfc822"
                putExtra(Intent.EXTRA_EMAIL, arrayOf(message.to))
                putExtra(Intent.EXTRA_SUBJECT, message.subject)
                putExtra(Intent.EXTRA_TEXT, message.message)
            }
                .apply { setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
                .apply { context.startActivity(this) }
        }.onFailure { showErrorToast("Gmail") }
    }

    actual fun sms(message: EnquiryMessageDomain) {
        kotlin.runCatching {
            Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:${Uri.encode(message.to)}")
                putExtra("sms_body", message.message)
            }
                .apply { setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
                .apply { context.startActivity(this) }
        }.onFailure { showErrorToast("SMS") }
    }

    actual fun whatsapp(message: EnquiryMessageDomain) {
        kotlin.runCatching {
            val whatsappUri = messageMapper.mapWhatsappUri(message)
            Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(whatsappUri)
            }
                .apply { setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
                .apply { context.startActivity(this) }
        }.onFailure { showErrorToast("WhatsApp") }
    }

    actual fun encode(text: String): String = Uri.encode(text)

    private fun showErrorToast(type: String) {
        Toast.makeText(context, "Couldn't launch $type", Toast.LENGTH_LONG).show()
    }

    actual fun call(phone: String) {
        Intent(Intent.ACTION_DIAL, Uri.parse(messageMapper.mapPhoneUri(phone)))
            .apply { setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
            .apply { context.startActivity(this) }

    }
}
