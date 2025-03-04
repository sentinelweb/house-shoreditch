package com.house_shoreditch.app.main

import androidx.lifecycle.ViewModel
import com.house_shoreditch.app.Secrets
import com.house_shoreditch.app.domain.BookingEnquiryDomain
import com.house_shoreditch.app.domain.EnquiryMessageDomain
import com.house_shoreditch.app.domain.PaymentMethod
import com.house_shoreditch.app.main.MainContract.BookingModel
import com.house_shoreditch.app.main.MainContract.Companion.BookingInitial
import com.house_shoreditch.app.main.MainContract.Companion.ContactInitial
import com.house_shoreditch.app.main.MainContract.Companion.DesktopDownloadUrl
import com.house_shoreditch.app.main.MainContract.ContactModel
import com.house_shoreditch.app.util.Launcher
import com.house_shoreditch.app.util.getPlatform
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.*

private const val MAX_PEOPLE = 7

@Suppress("TooManyFunctions")
class MainViewModel(
    private val launcher: Launcher,
    private val secrets: Secrets
) : ViewModel() {

    private val _bookingModel: MutableStateFlow<BookingModel> = MutableStateFlow(BookingInitial)
    val bookingModel: StateFlow<BookingModel> = _bookingModel

    private val _contactModel: MutableStateFlow<ContactModel> = MutableStateFlow(ContactInitial)
    val contactModel: StateFlow<ContactModel> = _contactModel

    fun openBooking() {
        launcher.open("https://www.booking.com/hotel/gb/inviting-3-bedroom-house-and-garden-in-shoreditch.en-gb.html")
    }

    fun openAirbnb() {
        launcher.open("https://www.airbnb.com/rooms/945464635020318901")
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
                } else null,
                error = null,
            )
        }
    }

    fun onChangeNumPeople(numPeople: Int) {
        if (numPeople in 0..MAX_PEOPLE) {
            _bookingModel.update {
                bookingModel.value.copy(numPeople = numPeople)
            }
        }
    }

    fun onSendEnquiryEmail() {
        if (!validate()) return
        EnquiryMessageDomain(
            to = secrets.email,
            from = null,
            subject = "Oasis website enquiry: ${bookingModel.value.dateRange?.first ?: "No date"}",
            message = generateEnquiryBody()
        ).let {
            launcher.mail(it)
        }
    }

    fun onSendEnquiryGmail() {
        if (!validate()) return
        EnquiryMessageDomain(
            to = secrets.email,
            from = null,
            subject = "Oasis website enquiry: ${bookingModel.value.dateRange?.first ?: "No date"}",
            message = generateEnquiryBody()
        ).let {
            launcher.gmail(it)
        }
    }

    fun onSendEnquirySms() {
        if (!validate()) return
        EnquiryMessageDomain(
            to = secrets.phone,
            from = null,
            subject = "",
            message = generateEnquiryBody()
        ).let {
            launcher.sms(it)
        }
    }

    fun onSendEnquiryWhatsApp() {
        if (!validate()) return
        EnquiryMessageDomain(
            to = secrets.phone,
            from = null,
            subject = "",
            message = generateEnquiryBody()
        ).let {
            launcher.whatsapp(it)
        }
    }

    private fun validate(): Boolean {
        if (bookingModel.value.dateRange == null) {
            _bookingModel.update { bookingModel.value.copy(error = "Please select some dates") }
            return false
        }
        return true
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
                Number of People: ${it.numPeople}
            """.trimIndent()
    }

    fun onDownloadPlayClick() {
        launcher.open("https://play.google.com/store/apps/details?id=com.oasis_shoreditch.app")
    }

    fun onDownloadAppleStoreClick() {
        launcher.open("https://www.apple.com/app-store/") // todo update when released
    }

    fun onDownloadMacClick() {
        launcher.open(DesktopDownloadUrl)
    }

    fun onDownloadWinClick() {
        launcher.open(DesktopDownloadUrl)
    }

    fun onDownloadLinuxClick() {
        launcher.open(DesktopDownloadUrl)
    }

    fun onDownloadWebClick() {
        launcher.open("https://oasis-shoreditch.com/")
    }

    fun onContactPhoneClick() {
        launcher.call(secrets.phone)
        _contactModel.update { contactModel.value.copy(phone = secrets.phone) }
    }

    fun onContactEmailClick() {
        EnquiryMessageDomain(
            to = secrets.email,
            from = null,
            subject = "Oasis website enquiry",
            message = ""
        ).let {
            if (getPlatform().isEmailAvailable) {
                launcher.mail(it)
            } else {
                launcher.gmail(it)
            }
        }
        _contactModel.update { contactModel.value.copy(email = secrets.email) }
    }

    fun onContactWhatsAppClick() {
        EnquiryMessageDomain(
            to = secrets.phone,
            from = null,
            subject = "",
            message = ""
        ).let {
            launcher.whatsapp(it)
        }
    }
}
