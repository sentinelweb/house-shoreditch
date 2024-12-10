package com.house_shoreditch.app

interface MainContract {

    data class Model(
        val homeBackground: String,
        val images: List<String>,
    ) {
        companion object {
            val Init: Model = Model("", listOf())
        }
    }
}
