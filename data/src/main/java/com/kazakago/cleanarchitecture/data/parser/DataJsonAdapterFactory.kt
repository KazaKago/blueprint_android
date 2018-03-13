package com.kazakago.cleanarchitecture.data.parser

import com.squareup.moshi.JsonAdapter
import se.ansman.kotshi.KotshiJsonAdapterFactory

@KotshiJsonAdapterFactory
abstract class DataJsonAdapterFactory : JsonAdapter.Factory {
    companion object {
        val INSTANCE: DataJsonAdapterFactory = KotshiDataJsonAdapterFactory()
    }
}