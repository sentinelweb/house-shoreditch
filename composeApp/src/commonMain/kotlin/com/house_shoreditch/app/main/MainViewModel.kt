package com.house_shoreditch.app.main

import androidx.lifecycle.ViewModel
import com.house_shoreditch.app.main.MainContract.BookingModel
import com.house_shoreditch.app.main.MainContract.Companion.BookingInit
import com.house_shoreditch.app.main.MainContract.Companion.Init
import com.house_shoreditch.app.main.MainContract.Model
import com.house_shoreditch.app.util.LinkLauncher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(
    private val linkLauncher: LinkLauncher = LinkLauncher()
): ViewModel() {

    val _model: MutableStateFlow<Model> = MutableStateFlow(Init)
    val model: StateFlow<Model> = _model

    val _bookingModel: MutableStateFlow<BookingModel> = MutableStateFlow(BookingInit)
    val bookingModel: StateFlow<BookingModel> = _bookingModel

    fun openBooking() {
        linkLauncher.open("https://www.booking.com/hotel/gb/inviting-3-bedroom-house-and-garden-in-shoreditch.en-gb.html")
    }

    fun openAirbnb() {
        linkLauncher.open("https://www.airbnb.com/rooms/945464635020318901")
    }
}
