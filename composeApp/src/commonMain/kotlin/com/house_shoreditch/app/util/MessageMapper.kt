package com.house_shoreditch.app.util

import com.house_shoreditch.app.domain.EnquiryMessageDomain
import com.house_shoreditch.app.domain.EnquiryMessageDomain.Companion.BODY_PLACEHOLDER
import com.house_shoreditch.app.domain.EnquiryMessageDomain.Companion.SUBJECT_PLACEHOLDER
import com.house_shoreditch.app.domain.EnquiryMessageDomain.Companion.TO_PLACEHOLDER
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MessageMapper : KoinComponent {

    private val launcher: Launcher by inject()

    fun mapGmailUrl(message: EnquiryMessageDomain) =
        EnquiryMessageDomain.GMAIL_URL
            .replace(TO_PLACEHOLDER, launcher.encode(message.to))
            .replace(SUBJECT_PLACEHOLDER, launcher.encode(message.subject))
            .replace(BODY_PLACEHOLDER, launcher.encode(message.message))

    fun mapGmailUrlUnencoded(message: EnquiryMessageDomain) =
        EnquiryMessageDomain.GMAIL_URL
            .replace(TO_PLACEHOLDER, message.to)
            .replace(SUBJECT_PLACEHOLDER, message.subject)
            .replace(BODY_PLACEHOLDER, message.message)

    fun mapMailUrl(message: EnquiryMessageDomain) =
        EnquiryMessageDomain.MAIL_URI
            .replace(TO_PLACEHOLDER, launcher.encode(message.to))
            .replace(SUBJECT_PLACEHOLDER, launcher.encode(message.subject))
            .replace(BODY_PLACEHOLDER, launcher.encode(message.message))

    fun mapSmsUrl(message: EnquiryMessageDomain) =
        EnquiryMessageDomain.SMS_URI
            .replace(TO_PLACEHOLDER, launcher.encode(message.to))
            .replace(BODY_PLACEHOLDER, launcher.encode(message.message))

    fun mapWhatsappClickChatUrl(message: EnquiryMessageDomain) =
        EnquiryMessageDomain.WHATSAPP_CLICK_CHAT_URI
            .replace(TO_PLACEHOLDER, message.to.substringAfter("+"))
            .replace(BODY_PLACEHOLDER, launcher.encode(message.message))

    fun mapWhatsappUri(message: EnquiryMessageDomain) =
        EnquiryMessageDomain.WHATSAPP_URI
            .replace(TO_PLACEHOLDER, message.to)
            .replace(BODY_PLACEHOLDER, message.message)

    fun mapPhoneUri(phone:String) = "tel:$phone"
}
