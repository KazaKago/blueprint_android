package com.kazakago.cleanarchitecture.data.resource.global

import com.squareup.moshi.Moshi

internal class MoshiBuilder {

    private val builder = Moshi.Builder()

    fun build(): Moshi {
        return builder.build()
    }

}