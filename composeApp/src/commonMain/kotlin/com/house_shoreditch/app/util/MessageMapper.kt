package com.house_shoreditch.app.util

import com.house_shoreditch.app.domain.EnquiryMessageDomain
import com.house_shoreditch.app.domain.EnquiryMessageDomain.Companion.BODY_PLACEHOLDER
import com.house_shoreditch.app.domain.EnquiryMessageDomain.Companion.SUBJECT_PLACEHOLDER
import com.house_shoreditch.app.domain.EnquiryMessageDomain.Companion.TO_PLACEHOLDER
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MessageMapper() : KoinComponent {

    private val linkLauncher: LinkLauncher by inject()

    fun mapGmailUrl(message: EnquiryMessageDomain) =
        EnquiryMessageDomain.GMAIL_URL
            .replace(TO_PLACEHOLDER, linkLauncher.encode(message.to))
            .replace(SUBJECT_PLACEHOLDER, linkLauncher.encode(message.subject))
            .replace(BODY_PLACEHOLDER, linkLauncher.encode(message.message))

    fun mapMailUrl(message: EnquiryMessageDomain) =
        EnquiryMessageDomain.MAIL_URI
            .replace(TO_PLACEHOLDER, linkLauncher.encode(message.to))
            .replace(SUBJECT_PLACEHOLDER, linkLauncher.encode(message.subject))
            .replace(BODY_PLACEHOLDER, linkLauncher.encode(message.message))


    fun mapSmsUrl(message: EnquiryMessageDomain) =
        EnquiryMessageDomain.SMS_URI
            .replace(TO_PLACEHOLDER, linkLauncher.encode(message.to))
            .replace(BODY_PLACEHOLDER, linkLauncher.encode(message.message))
}
