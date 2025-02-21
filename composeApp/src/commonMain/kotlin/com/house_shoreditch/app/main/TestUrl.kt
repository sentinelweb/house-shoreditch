package com.house_shoreditch.app.main

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*

class TestUrl {
    suspend fun get(url:String) {
        val client = HttpClient(CIO)

        try {
            // Specify the URL of the file
            //val fileUrl = "https://example.com/sample-file.txt"
            // The path where the file will be saved
            //val filePath = "downloaded-file.txt"

            // Make the GET request and write the response to a file
            client.get(url) {
                //val file = File(filePath)
//                file.writeBytes(responseBytes)
//                println("File downloaded successfully to ${file.absolutePath}")
            }
                .also { println(it.body<String>()) }
        } catch (e: Exception) {
            println("File download failed: ${e.message}")
        } finally {
            // Close the client
            client.close()
        }
    }
}
