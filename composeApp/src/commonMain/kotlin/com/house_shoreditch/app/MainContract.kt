package com.house_shoreditch.app

interface MainContract {

    data class Model(
        val homeBackground: String,
        val images: List<String>,
        val areas: List<Area>,
    ) {
        data class Area(
            val name: String,
            val fetures: List<Feature>
        ) {

            data class Feature(
                val title: String,
                val description: String,
            )
        }

        companion object {
            val Areas: List<Area> =
                (1..11).map {
                    Area(
                        name = "Living room",
                        fetures = listOf(
                            Area.Feature(
                                title = "4K Projector w. B&O soundbar",
                                description = "Delivers an outstanding movie experience with adapters for connecting your devices"
                            ),
                            Area.Feature(
                                title = "Garden access",
                                description = "The large bifoild door blurs inside and out"
                            ),
                            Area.Feature(
                                title = "Underfloor heating",
                                description = "The ground floor ahs underfloor heating throughout with WiFi thermostats"
                            ),
                        )
                    )
                }

            val Init: Model = Model("", listOf(), Areas)

        }
    }
}
