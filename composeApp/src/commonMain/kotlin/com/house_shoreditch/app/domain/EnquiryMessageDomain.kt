package com.house_shoreditch.app.domain

data class EnquiryMessageDomain (
    val to: String,
    val from: String?,
    val subject: String,
    val message: String,
) {
    companion object {
        val TO_PLACEHOLDER = "<TO>"
        val SUBJECT_PLACEHOLDER = "<SUBJECT>"
        val BODY_PLACEHOLDER = "<BODY>"
        val GMAIL_URL = "https://mail.google.com/mail/?view=cm&fs=1" +
                "&to=$TO_PLACEHOLDER" +
                "&su=$SUBJECT_PLACEHOLDER" +
                "&body=$BODY_PLACEHOLDER"
        val MAIL_URI= "mailto:$TO_PLACEHOLDER?subject=$SUBJECT_PLACEHOLDER&body=$BODY_PLACEHOLDER"
        val SMS_URI = "sms:$TO_PLACEHOLDER?body=$BODY_PLACEHOLDER"
    }
}

