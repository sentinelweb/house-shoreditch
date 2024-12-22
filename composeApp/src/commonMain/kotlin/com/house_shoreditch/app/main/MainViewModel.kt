package com.house_shoreditch.app.main

import androidx.lifecycle.ViewModel
import com.house_shoreditch.app.main.MainContract.Model.Companion.Areas
import com.house_shoreditch.app.main.MainContract.Model.Companion.Reviews
import com.house_shoreditch.app.util.LinkLauncher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(
    private val linkLauncher: LinkLauncher = LinkLauncher()
): ViewModel() {

    val _model: MutableStateFlow<MainContract.Model> = MutableStateFlow(MainContract.Model.Init)
    val model: StateFlow<MainContract.Model> = _model

    init {
        val imageBasePath = "https://raw.githubusercontent.com/sentinelweb/house-shoreditch/refs/heads/main/media/photos"
        val imageList = listOf(
            "living_1.png",
            "living_2.png",
            "living_2_DSC_0274.JPG",
            "living_3.png",
            "living_4.png",
            "living_5.png",
            "lounge_1.png",
            "lounge_6_DSC_0277.JPG",
            "garden_6_DSC_0292.JPG",
            "garden_7_DSC_0291.JPG",
            "garden_8_DSC_0279.JPG",
            "garden_1.png",
            "garden_1_DSC_0295.JPG",
            "garden_2.png",
            "garden_3.png",
            "garden_4.png",
            "garden_5.png",
            "dining_1.png",
            "dining_2.png",
            "dining_3.png",
            "b4_1.png",
            "b4_2.png",
            "b4_3.png",
            "bath_1.png",
            "bath_2.png",
            "bath_3.png",
            "bath_4.png",
            "bath_5.png",
            "bbq_1.png",
            "bbq_2.png",
            "bed_1_1.png",
            "bed_1_2.png",
            "bed_1_3.png",
            "bed_1_4.png",
            "bed_3_1.png",
            "bed_3_2.png",
            "bed_3_3.png",
            "bed_3_4.png",
            "hall_1.png",
            "hall_2.png",
            "hall_3.png",
            "hall_4.png",
            "hall_5.png",
            "hall_6.png",
            "kitchen_1.png",
            "kitchen_2.png",
        )
        _model.value = MainContract.Model(
            homeBackground = "$imageBasePath/garden_7_DSC_0291.JPG",
            images = imageList.map { "$imageBasePath/$it" },
            areas = Areas,
            reviews = Reviews,
        )
    }

    fun openBooking() {
        linkLauncher.open("https://www.booking.com/hotel/gb/inviting-3-bedroom-house-and-garden-in-shoreditch.en-gb.html")
    }

    fun openAirbnb() {
        linkLauncher.open("https://www.airbnb.com/rooms/945464635020318901")
    }
}
