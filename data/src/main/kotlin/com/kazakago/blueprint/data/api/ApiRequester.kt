package com.kazakago.blueprint.data.api

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ApiRequester @Inject constructor() {

    private val baseUrl = "https://api.github.com"

    private val contentType = ContentType.Application.Json

    private val serialFormatter = Json {
        ignoreUnknownKeys = true
    }

    val httpClient = HttpClient(CIO) {
        defaultRequest {
            url(baseUrl)
            header(HttpHeaders.ContentType, contentType)
        }
        install(ContentNegotiation) {
            json(serialFormatter)
        }
    }
}
