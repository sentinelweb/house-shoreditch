package com.house_shoreditch.app.main

import com.house_shoreditch.app.domain.PaymentMethod
import com.house_shoreditch.app.domain.PaymentMethod.Pounds
import com.house_shoreditch.app.main.MainContract.Model.Area
import com.house_shoreditch.app.main.MainContract.Model.Review
import org.jetbrains.compose.resources.DrawableResource
import oasis.composeapp.generated.resources.*

interface MainContract {

    data class Model(
        val homeBackgroundUris: List<String>,
        val imageUris: List<String>,
        val areas: List<Area>,
        val reviews: List<Review>,
        val mapImageUri: String
    ) {
        data class Area(
            val name: String,
            val icon: DrawableResource,
            val features: List<Feature>
        ) {

            data class Feature(
                val title: String,
                val icon: DrawableResource,
            )
        }

        data class Review(
            val author: String,
            val rating: Int,
            val review: String,
        )
    }

    data class BookingModel(
        val dateRange: Pair<String, String>?,
        val paymentMethods: Set<PaymentMethod>,
        val numPeople: Int,
        val error: String?
    )

    data class ContactModel(
        val phone: String?,
        val email: String?,
    )

    @Suppress("MaxLineLength")
    companion object {
        const val DesktopDownloadUrl = "https://github.com/sentinelweb/house-shoreditch/releases/latest"
        const val ImageBasePath =
            "https://raw.githubusercontent.com/sentinelweb/house-shoreditch/refs/heads/main/media/"
        val ImageList = listOf(
            "living_1_abb.jpg",
            "living_2_abb.jpg",
            "living_3_abb.jpg",
            "living_4_abb.jpg",
            "living_5_abb.jpg",
            "living_1.png",
            "living_2.png",
            "living_2_DSC_0274.JPG",
            "living_3.png",
            "living_4.png",
            "living_5.png",
            "lounge_1.png",
            "lounge_6_DSC_0277.JPG",
            "garden_1_abb.jpg",
            "garden_2_abb.jpg",
            "garden_3_abb.jpg",
            "garden_4_abb.jpg",
            "garden_1.png",
            "garden_1_DSC_0295.JPG",
            "garden_2.png",
            "garden_3.png",
            "garden_4.png",
            "garden_5.png",
            "garden_6_DSC_0292.JPG",
            "garden_7_DSC_0291.JPG",
            "garden_8_DSC_0279.JPG",
            "dining_1.png",
            "dining_2.png",
            "dining_3.png",
            "kitchen_1_abb.jpg",
            "kitchen_2_abb.jpg",
            "kitchen_3_abb.jpg",
            "kitchen_4_abb.jpg",
            "kitchen_1.png",
            "kitchen_2.png",
            "bbq_1.png",
            "bbq_2.png",
            "bed_1_1_abb.jpg",
            "bed_1_2_abb.jpg",
            "bed_1_3_abb.jpg",
            "bed_1_4_abb.jpg",
            "bed_1_1.png",
            "bed_1_2.png",
            "bed_1_3.png",
            "bed_1_4.png",
            "bed_3_1_abb.jpg",
            "bed_3_2_abb.jpg",
            "bed_3_3_abb.jpg",
            "bed_3_4_abb.jpg",
            "bed_3_5_abb.jpg",
            "bed_3_1.png",
            "bed_3_2.png",
            "bed_3_3.png",
            "bed_3_4.png",
            "bed_4_1_abb.jpg",
            "bed_4_2_abb.jpg",
            "bed_4_1.png",
            "bed_4_2.png",
            "bed_4_3.png",
            "bath_1_abb.jpg",
            "bath_2_abb.jpg",
            "bath_3_abb.jpg",
            "bath_1.png",
            "bath_2.png",
            "bath_3.png",
            "bath_4.png",
            "bath_5.png",
            "hall_1_abb.jpg",
            "hall_2_abb.jpg",
            "hall_1.png",
            "hall_2.png",
            "hall_3.png",
            "hall_4.png",
            "hall_5.png",
            "hall_6.png",
        )

        val Areas: List<Area> =
            listOf(
                Area(
                    name = "Living room",
                    icon = Res.drawable.living,
                    features = listOf(
                        Area.Feature(
                            title = "4K Projector",
                            icon = Res.drawable.smart_display
                        ),
                        Area.Feature(
                            title = "Bang & Olufsen soundbar",
                            icon = Res.drawable.speaker
                        ),
                        Area.Feature(
                            title = "Chromecast",
                            icon = Res.drawable.cast
                        ),
                        Area.Feature(
                            title = "2 Large sofas",
                            icon = Res.drawable.chair
                        ),
                        Area.Feature(
                            title = "Underfloor heating",
                            icon = Res.drawable.heat
                        ),
                        Area.Feature(
                            title = "Fibre internet 1Ghz",
                            icon = Res.drawable.wifi
                        ),
                        Area.Feature(
                            title = "Smart controls",
                            icon = Res.drawable.smart_controls
                        ),
                        Area.Feature(
                            title = "Tablet manual & control",
                            icon = Res.drawable.tablet
                        ),
                        Area.Feature(
                            title = "Charging station",
                            icon = Res.drawable.charging_station
                        ),
                        Area.Feature(
                            title = "Thermostat",
                            icon = Res.drawable.thermostat
                        ),
                        Area.Feature(
                            title = "Dining table",
                            icon = Res.drawable.table_bar
                        ),
                    )
                ),
                Area(
                    name = "Kitchen",
                    icon = Res.drawable.kitchen,
                    features = listOf(
                        Area.Feature(
                            title = "Oven",
                            icon = Res.drawable.oven
                        ),
                        Area.Feature(
                            title = "Cooker",
                            icon = Res.drawable.cooking
                        ),
                        Area.Feature(
                            title = "Automatic hood",
                            icon = Res.drawable.range_hood
                        ),
                        Area.Feature(
                            title = "Fridge",
                            icon = Res.drawable.fridge
                        ),
                        Area.Feature(
                            title = "Blender",
                            icon = Res.drawable.blender
                        ),
                        Area.Feature(
                            title = "Sink",
                            icon = Res.drawable.countertops
                        ),
                        Area.Feature(
                            title = "Kettle",
                            icon = Res.drawable.kettle
                        ),
                        Area.Feature(
                            title = "Microwave",
                            icon = Res.drawable.microwave
                        ),
                        Area.Feature(
                            title = "Dishwasher",
                            icon = Res.drawable.dishwasher
                        ),
                        Area.Feature(
                            title = "Bean to cup coffee machine",
                            icon = Res.drawable.coffee_maker
                        )
                    )
                ),
                Area(
                    name = "Bed #1",
                    icon = Res.drawable.bedroom,
                    features = listOf(
                        Area.Feature(
                            title = "King bed",
                            icon = Res.drawable.bed
                        ),
                        Area.Feature(
                            title = "Desk",
                            icon = Res.drawable.desk
                        ),
                        Area.Feature(
                            title = "Lamp",
                            icon = Res.drawable.table_lamp
                        ),
                        Area.Feature(
                            title = "Bedside table",
                            icon = Res.drawable.drawer
                        ),
                        Area.Feature(
                            title = "Wardrobe",
                            icon = Res.drawable.wardrobe
                        ),
                        Area.Feature(
                            title = "Chair",
                            icon = Res.drawable.chair
                        ),
                        Area.Feature(
                            title = "TV",
                            icon = Res.drawable.tv
                        ),
                        Area.Feature(
                            title = "Linen",
                            icon = Res.drawable.linen
                        )
                    ),
                ),
                Area(
                    name = "Bed #2",
                    icon = Res.drawable.bedroom,
                    features = listOf(
                        Area.Feature(
                            title = "Double Wall bed",
                            icon = Res.drawable.bed
                        ),
                        Area.Feature(
                            title = "Desk",
                            icon = Res.drawable.desk
                        ),
                        Area.Feature(
                            title = "Lamp",
                            icon = Res.drawable.table_lamp
                        ),
                        Area.Feature(
                            title = "Hanging space",
                            icon = Res.drawable.hanging_space
                        ),
                        Area.Feature(
                            title = "Drawers",
                            icon = Res.drawable.drawer
                        ),
                        Area.Feature(
                            title = "Linen",
                            icon = Res.drawable.linen
                        ),
                    )
                ),
                Area(
                    name = "Bed #3",
                    icon = Res.drawable.bedroom,
                    features = listOf(
                        Area.Feature(
                            title = "Double Wall bed",
                            icon = Res.drawable.bed
                        ),
                        Area.Feature(
                            title = "Desk",
                            icon = Res.drawable.desk
                        ),
                        Area.Feature(
                            title = "Lamp",
                            icon = Res.drawable.table_lamp
                        ),
                        Area.Feature(
                            title = "Hanging space",
                            icon = Res.drawable.hanging_space
                        ),
                        Area.Feature(
                            title = "Shelves",
                            icon = Res.drawable.shelves
                        ),
                        Area.Feature(
                            title = "Linen",
                            icon = Res.drawable.linen
                        ),
                    )
                ),
                Area(
                    name = "Bathroom #1",
                    icon = Res.drawable.bathroom,
                    features = listOf(
                        Area.Feature(
                            title = "Bath",
                            icon = Res.drawable.bathtub
                        ),
                        Area.Feature(
                            title = "Shower",
                            icon = Res.drawable.shower
                        ),
                        Area.Feature(
                            title = "Sink",
                            icon = Res.drawable.countertops
                        ),
                        Area.Feature(
                            title = "Toilet",
                            icon = Res.drawable.toilet
                        ),
                        Area.Feature(
                            title = "Self care",
                            icon = Res.drawable.self_care
                        ),
                    )
                ),
                Area(
                    name = "Bathroom #2",
                    icon = Res.drawable.bathroom,
                    features = listOf(
                        Area.Feature(
                            title = "Shower",
                            icon = Res.drawable.shower
                        ),
                        Area.Feature(
                            title = "Sink",
                            icon = Res.drawable.countertops
                        ),
                        Area.Feature(
                            title = "Toilet",
                            icon = Res.drawable.toilet
                        ),
                        Area.Feature(
                            title = "Self care",
                            icon = Res.drawable.self_care
                        ),
                    )
                ),
                Area(
                    name = "Garden",
                    icon = Res.drawable.yard,
                    features = listOf(
                        Area.Feature(
                            title = "Large bifold door",
                            icon = Res.drawable.door_sliding
                        ),
                        Area.Feature(
                            title = "Deck",
                            icon = Res.drawable.deck
                        ),
                        Area.Feature(
                            title = "Outdoor sofa",
                            icon = Res.drawable.chair
                        ),
                        Area.Feature(
                            title = "BBQ",
                            icon = Res.drawable.outdoor_grill
                        ),
                        Area.Feature(
                            title = "Afternoon sun",
                            icon = Res.drawable.sun
                        ),
                        Area.Feature(
                            title = "Garden beds",
                            icon = Res.drawable.plant
                        ),
                    )
                ),
                Area(
                    name = "All areas",
                    icon = Res.drawable.hallway,
                    features = listOf(
                        Area.Feature(
                            title = "USB Power sockets",
                            icon = Res.drawable.usb
                        ),
                        Area.Feature(
                            title = "Nest thermostat",
                            icon = Res.drawable.nest_thermostat
                        ),
                        Area.Feature(
                            title = "Smart Lock",
                            icon = Res.drawable.mfg_nest_yale_lock
                        ),
                        Area.Feature(
                            title = "Water heater",
                            icon = Res.drawable.water_heater
                        ),
                    )
                ),
                Area(
                    name = "Laundry",
                    icon = Res.drawable.laundry,
                    features = listOf(
                        Area.Feature(
                            title = "Washing machine",
                            icon = Res.drawable.washing_machine
                        ),
                        Area.Feature(
                            title = "Dryer",
                            icon = Res.drawable.tumble_dryer
                        ),
                    )
                ),
            )

        val Reviews = listOf(
            Review(
                author = "Clarke",
                rating = 5,
                review = """Robert's home was a lovely place to stay. We were there for nine days with a 1-year-old baby. It wasn't some corporate-run Airbnb...it felt like staying in someone's truly well-kept home. The photos don't do it justice! Kitchen was huge and so well-stocked. The outdoor space felt like I was stepping into a tropical paradise! If only the British weather had cooperated and let us spend a bit more time out there.
Just to note that the bathroom on the ground floor is very small, but so well thought out. Even so, it's great to have the flexibility of two showers—and perfect for one member of our party who would have struggled with stepping into tub in upstairs bathroom.
I'm home and already wishing I were back!"""
            ),
            Review(
                author = "Lindsey",
                rating = 5,
                review = """In London from the States for a family vacation with my husband and three children. Robert was responsive, helpful, and friendly. He helped with a change in travel itinerary. House was extremely comfortable and incredibly well equipped. The espresso maker was delightful!!! A great place to stay close to the underground and in a really cool neighborhood!!!"""
            ),
            Review(
                author = "Cillian & Lauren",
                rating = 5,
                review = """Robert’s place was perfect, a lovely home with private garden, only 10 minutes walk to the tube. The Shoreditch Hoxton area has so much going on and it’s easy to grab food or drinks in the neighborhood. We found the house very well equipped with everything we could need including some extra toiletries, towels and made use of the washer and dryer. This spot was excellent for our group and we would love to come back. Thanks again!"""
            ),
            Review(
                author = "Loïc",
                rating = 5,
                review = """Family stay at 8 in April. Great apartment very well located in a nice and lively neighborhood, just what you need. Overlooking a small park on one side and the street on the other, the apartment is easy to access with the metro nearby, on a line that makes it easy and quick to get to the tourist sites. The amenities mentioned are all there and are of quality. Robert was very kind and welcomed us very warmly. We were even able to enjoy the patio, very nice, with a ray of sunshine.
We would recommend it."""
            ),
            Review(
                author = "John",
                rating = 5,
                review = """We had a beautiful stay in the house. It was very clean and has everything we need to cook as well as just stay in and watch a movie with amazing 4k projection and great sound. The garden was also great and we got some winter sun in the afternoon.
Rob was very helpful and got back right away if we need anything. We'll stay again when we are in town! Thanks for everything."""
            ),
            Review(
                author = "Lisa",
                rating = 5,
                review = """The house was wonderful, close to public transportation. It had everything we needed for an enjoyable holiday. This was my first time using AB&B, after this stay I will definitely do it again."""
            ),
            Review(
                author = "Bev",
                rating = 5,
                review = """Robert’s place was perfect for our family vacation in London. We were staying at hotels the week leading up to our stay in London and was tired of eating out every meal. Robert’s place allowed our family to cook/reheat ready-to-eat meals we picked up at grocery stores. There are many options for getting food nearby, from the larger M&S food and Aldi to the smaller Tesco express and Sainsbury local, all within walking distance. Robert’s kitchen is well stocked and had everything we needed.
Robert responded quickly to all of our questions. He was also very accommodating to our needs. By letting us do luggage store, we were able to drop off our bags at the house before dropping off the rental car.
We were a family of 5 adults and 1 child, and Robert’s place was very spacious and comfortable. The separate washer and dryer was a huge bonus for us."""
            ),
            Review(
                author = "Tom",
                rating = 5,
                review = """Great stay, Robert's communication is excellent and the house is really well equipped (cooking equipment, technology etc all exceptional)."""
            )
        )
        val ModelStatic: Model = Model(
            homeBackgroundUris = listOf(
                "garden_7_DSC_0291.JPG",
                "living_1_abb.jpg",
                "living_3_abb.jpg",
                "garden_1_abb.jpg",
                "garden_6_DSC_0292.JPG",
            ).map { "$ImageBasePath/photos/$it" },
            imageUris = ImageList.map { "$ImageBasePath/photos/$it" },
            areas = Areas,
            reviews = Reviews,
            mapImageUri = "$ImageBasePath/other/map.png",
        )
        const val NumberOfPeopleInitial: Int = 4
        val BookingInitial = BookingModel(
            dateRange = null,
            paymentMethods = setOf(Pounds),
            numPeople = NumberOfPeopleInitial,
            error = null,
        )
        val ContactInitial = ContactModel(
            phone = null,
            email = null,
        )
    }
}
