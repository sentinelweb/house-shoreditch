package com.house_shoreditch.app.util

import com.house_shoreditch.app.domain.EnquiryMessageDomain
import com.house_shoreditch.app.util.launchers.EmailLauncher
import com.house_shoreditch.app.util.launchers.SMSLauncher
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual class Launcher : KoinComponent {

    private val messageMapper: MessageMapper by inject()
    private val smsLauncher: SMSLauncher by inject()
    private val emailLauncher: EmailLauncher by inject()

    actual fun open(url: String) {
        internalOpen(url)
    }

    private fun internalOpen(url: String, fallback:(() -> Unit)? = null) {
        val nsUrl = NSURL(string = url)
        if (UIApplication.sharedApplication.canOpenURL(nsUrl)) {
            UIApplication.sharedApplication.openURL(nsUrl, options = emptyMap<Any?, Any>()) { success ->
                if (success) {
                    println("successfully opened: $url")
                } else {
                    fallback?.invoke()
                    println("Failed to open: $url")
                }
            }
        } else {
            println("Invalid URL or cannot open the URL.")
        }
    }

    // todo check for ios encoder function
    @Suppress("MagicNumber")
    actual fun encode(text: String): String {
        return text.map { char ->
            when (char) {
                in 'A'..'Z', in 'a'..'z', in '0'..'9', '-', '_', '.', '~' -> char.toString()
                else -> "%${char.code.toString(16).uppercase()}"
            }
        }.joinToString("")
    }

    actual fun mail(message: EnquiryMessageDomain) {
        emailLauncher.composeEmail(message.to, message.subject, message.message)
    }

    actual fun gmail(message: EnquiryMessageDomain) {
        val url = "googlegmail:///co?to=${message.to}&subject=${message.subject}&body=${message.message}"
        internalOpen(url) {
            // fallback
            internalOpen(messageMapper.mapGmailUrlUnencoded(message))
        }
    }

    actual fun sms(message: EnquiryMessageDomain) {
        smsLauncher.sendSMS(message.to, message.message)
    }

    actual fun whatsapp(message: EnquiryMessageDomain) {
        val url = messageMapper.mapWhatsappUri(message)
        internalOpen(url) {
            // fallback
            internalOpen(messageMapper.mapWhatsappClickChatUrl(message))
        }
    }

    actual fun call(phone: String) {
        internalOpen(messageMapper.mapPhoneUri(phone))
    }
}
