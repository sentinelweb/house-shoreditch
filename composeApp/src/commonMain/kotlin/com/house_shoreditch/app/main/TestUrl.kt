package com.house_shoreditch.app.main

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*

// testing code for internet connection
class TestUrl {
    suspend fun get(url:String) {
        val client = HttpClient(CIO)

        try {
            client.get(url) {
            }.also { println(it.body<String>()) }
        } catch (e: Exception) {
            println("File download failed: ${e.message}")
        } finally {
            // Close the client
            client.close()
        }
    }
}
