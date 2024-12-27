package com.house_shoreditch.app.util

import com.house_shoreditch.app.domain.EnquiryMessageDomain
import com.house_shoreditch.app.util.launchers.EmailLauncher
import com.house_shoreditch.app.util.launchers.SMSLauncher
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual class LinkLauncher : KoinComponent {

    private val messageMapper: MessageMapper by inject()
    private val smsLauncher: SMSLauncher by inject()
    private val emailLauncher: EmailLauncher by inject()

    actual fun open(url: String) {
        val nsUrl = NSURL(string = url)
        if (UIApplication.sharedApplication.canOpenURL(nsUrl)) {
            UIApplication.sharedApplication.openURL(nsUrl)
        } else {
            println("Invalid URL or cannot open the URL.")
        }
    }

    // todo check for ios encoder function
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
    }

    actual fun sms(message: EnquiryMessageDomain) {
        smsLauncher.sendSMS(message.to, message.message)
    }
}
