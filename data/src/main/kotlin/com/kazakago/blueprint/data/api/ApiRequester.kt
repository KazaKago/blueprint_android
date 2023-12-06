package com.kazakago.blueprint.data.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ApiRequester @Inject constructor() {

    private val serialFormatter = Json {
        ignoreUnknownKeys = true
    }

    val httpClient = HttpClient(CIO) {
        defaultRequest {
            url("http://localhost:8080")
            contentType(ContentType.Application.Json)
        }
        install(ContentNegotiation) {
            json(serialFormatter)
        }
    }
}
