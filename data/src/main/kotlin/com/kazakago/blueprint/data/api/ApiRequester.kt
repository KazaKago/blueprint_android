package com.kazakago.blueprint.data.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRequester @Inject constructor() {

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
