package com.kazakago.cleanarchitecture.web.parser

import com.squareup.moshi.JsonAdapter
import se.ansman.kotshi.KotshiJsonAdapterFactory

@KotshiJsonAdapterFactory
abstract class WebJsonAdapterFactory : JsonAdapter.Factory {
    companion object {
        val INSTANCE: WebJsonAdapterFactory = KotshiWebJsonAdapterFactory()
    }
}