package com.house_shoreditch.app.util

import com.house_shoreditch.app.domain.EnquiryMessageDomain

expect class LinkLauncher() {
    fun open(url: String)
    fun encode(text: String): String
    fun mail(message: EnquiryMessageDomain)
    fun gmail(message: EnquiryMessageDomain)
    fun sms(message: EnquiryMessageDomain)
}
