package com.house_shoreditch.app.domain

data class BookingEnquiryDomain (
    val dateRange: Pair<String, String>,
    val paymentMethods: Set<PaymentMethod>,
    val numPeople: Int,
)
