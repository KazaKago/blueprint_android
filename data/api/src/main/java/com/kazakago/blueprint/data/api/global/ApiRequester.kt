package com.kazakago.blueprint.data.api.global

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
internal class ApiRequester @Inject constructor() {

    private val retrofit: Retrofit

    init {
        val moshi = Moshi.Builder().build()
        val moshiConverter = MoshiConverterFactory.create(moshi)
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(moshiConverter)
            .build()
    }

    fun <T : Any> create(kClass: KClass<T>): T {
        return retrofit.create(kClass.java)
    }
}
