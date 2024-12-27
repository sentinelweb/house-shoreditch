package com.house_shoreditch.app.util

import com.house_shoreditch.app.domain.EnquiryMessageDomain
import kotlinx.browser.window
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

external fun encodeURIComponent(text: String): String

actual class LinkLauncher : KoinComponent {
    private val messageMapper: MessageMapper by inject()

    actual fun open(url: String) {
        window.open(url, target = "_blank")
    }

    actual fun encode(text: String): String = encodeURIComponent(text)

    actual fun mail(message: EnquiryMessageDomain) {
        val mailToUri = messageMapper.mapMailUrl(message)
        open(mailToUri)
    }

    actual fun gmail(message: EnquiryMessageDomain) {
        val mailToUri = messageMapper.mapGmailUrl(message)
        open(mailToUri)
    }

    actual fun sms(message: EnquiryMessageDomain) {
        open(messageMapper.mapSmsUrl(message))
    }
}
