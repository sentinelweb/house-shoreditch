package com.house_shoreditch.app.main

import androidx.lifecycle.ViewModel
import com.house_shoreditch.app.domain.BookingEnquiryDomain
import com.house_shoreditch.app.domain.EnquiryMessageDomain
import com.house_shoreditch.app.domain.PaymentMethod
import com.house_shoreditch.app.main.MainContract.BookingModel
import com.house_shoreditch.app.main.MainContract.Companion.BookingInit
import com.house_shoreditch.app.util.LinkLauncher
import com.house_shoreditch.app.util.MessageMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.*
import uk.co.sentinelweb.cuer.hub.Secrets

class MainViewModel(
    private val linkLauncher: LinkLauncher,
    private val secrets: Secrets
) : ViewModel() {

    private val _bookingModel: MutableStateFlow<BookingModel> = MutableStateFlow(BookingInit)
    val bookingModel: StateFlow<BookingModel> = _bookingModel

    fun openBooking() {
        linkLauncher.open("https://www.booking.com/hotel/gb/inviting-3-bedroom-house-and-garden-in-shoreditch.en-gb.html")
    }

    fun openAirbnb() {
        linkLauncher.open("https://www.airbnb.com/rooms/945464635020318901")
    }

    fun onClickPayemntMethod(method: PaymentMethod) {
        _bookingModel.update {
            bookingModel.value.copy(
                paymentMethods = (if (bookingModel.value.paymentMethods.contains(method))
                    bookingModel.value.paymentMethods.minus(method)
                else bookingModel.value.paymentMethods.plus(method))
                    .also { println(it) }
            )
        }
    }

    fun onDatesSelected(dateRange: Pair<Long?, Long?>) {
        val formatter = LocalDateTime.Format { date(LocalDate.Formats.ISO) }

        val start = dateRange.first
            ?.let { Instant.fromEpochMilliseconds(it) }
            ?.toLocalDateTime(TimeZone.currentSystemDefault())

        val end = dateRange.second
            ?.let { Instant.fromEpochMilliseconds(it) }
            ?.toLocalDateTime(TimeZone.currentSystemDefault())

        _bookingModel.update {
            bookingModel.value.copy(
                dateRange = if (start != null && end != null) {
                    start.format(formatter) to end.format(formatter)
                } else null
            )
        }
    }

    fun onChangeNumPeople(numPeople: Int) {
        if (numPeople in 0..7) {
            _bookingModel.update {
                bookingModel.value.copy(numPeople = numPeople)
            }
        }
    }

    fun onSendEnquiryEmail() {
        EnquiryMessageDomain(
            to = secrets.email,
            from = null,
            subject = "Osric website enquiry: ${bookingModel.value.dateRange?.first ?: "No date"}",
            message = generateEnquiryBody()
        ).let {
            linkLauncher.mail(it)
        }
    }

    fun onSendEnquiryGmail() {
        EnquiryMessageDomain(
            to = secrets.email,
            from = null,
            subject = "Osric website enquiry: ${bookingModel.value.dateRange?.first ?: "No date"}",
            message = generateEnquiryBody()
        ).let {
            linkLauncher.gmail(it)
        }
    }

    fun onSendEnquirySms() {
        EnquiryMessageDomain(
            to = secrets.phone,
            from = null,
            subject = "",
            message = generateEnquiryBody()
        ).let {
            linkLauncher.sms(it)
        }
    }

    fun onSendEnquiryWhatsApp() {
        EnquiryMessageDomain(
            to = secrets.phone,
            from = null,
            subject = "",
            message = generateEnquiryBody()
        ).let {
            linkLauncher.whatsapp(it)
        }
    }

    // todo mapper object
    private fun generateEnquiryBody(): String = BookingEnquiryDomain(
        dateRange = bookingModel.value.dateRange ?: ("None" to "None"),
        paymentMethods = bookingModel.value.paymentMethods,
        numPeople = bookingModel.value.numPeople,
    ).let {
        """
                From: ${it.dateRange.first}
                To: ${it.dateRange.second}
                PaymentMethods: ${it.paymentMethods}
                # People: ${it.numPeople}
            """.trimIndent()
    }


}
