package com.house_shoreditch.app.util

import com.house_shoreditch.app.domain.EnquiryMessageDomain
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.awt.Desktop
import java.net.URI
import java.net.URLEncoder

actual class LinkLauncher : KoinComponent {

    private val messageMapper: MessageMapper by inject()

    actual fun open(url: String) {
        if (Desktop.isDesktopSupported()) {
            val desktop = Desktop.getDesktop()
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(URI(url))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                println("BROWSE action is not supported on this system.")
            }
        } else {
            println("Desktop is not supported on this system.")
        }
    }

    actual fun encode(text: String): String = URLEncoder.encode(text, "utf-8")

    actual fun mail(message: EnquiryMessageDomain) {
        val mailToUri = messageMapper.mapMailUrl(message)
        if (Desktop.isDesktopSupported()) {
            val desktop = Desktop.getDesktop()
            if (desktop.isSupported(Desktop.Action.MAIL)) {
                try {
                    desktop.mail(URI(mailToUri))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                println("MAIL action is not supported on this system.")
            }
        } else {
            println("Desktop is not supported on this system.")
        }
    }

    actual fun gmail(message: EnquiryMessageDomain) {
        messageMapper.mapGmailUrl(message)
            .apply { open(this) }
    }

    actual fun sms(message: EnquiryMessageDomain) {
        println("Not supported on this platform")
    }

    actual fun whatsapp(message: EnquiryMessageDomain) {
        open(messageMapper.mapWhatsappClickChatUrl(message))
    }
}
