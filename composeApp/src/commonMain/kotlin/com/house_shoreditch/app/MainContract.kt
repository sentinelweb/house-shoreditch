package com.house_shoreditch.app

interface MainContract {

    data class Model(
        val images: List<String>,
    ) {
        companion object {
            val Init: Model = Model(listOf())
        }
    }
}
