package com.kazakago.blueprint.data.api.global

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
internal class ApiRequester @Inject constructor() {

    private val baseUrl = URL("https://api.github.com")

    private val serialFormatter = Json {
        ignoreUnknownKeys = true
    }

    private val contentType = "application/json".toMediaType()

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(serialFormatter.asConverterFactory(contentType))
        .build()

    fun <T : Any> create(kClass: KClass<T>): T {
        return retrofit.create(kClass.java)
    }
}
