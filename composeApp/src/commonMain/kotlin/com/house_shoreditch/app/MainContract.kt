package com.house_shoreditch.app

import org.jetbrains.compose.resources.DrawableResource
import osric.composeapp.generated.resources.*
import osric.composeapp.generated.resources.Res
import osric.composeapp.generated.resources.cast
import osric.composeapp.generated.resources.heat
import osric.composeapp.generated.resources.yard

interface MainContract {

    data class Model(
        val homeBackground: String,
        val images: List<String>,
        val areas: List<Area>,
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

        companion object {
            val Areas: List<Area> =
                //(1..11).map {
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
//                }

            val Init: Model = Model("", listOf(), Areas)

        }
    }
}
