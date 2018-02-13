package com.kazakago.cleanarchitecture.data.parser

import com.squareup.moshi.Moshi

class MoshiBuilder {

    private val builder = Moshi.Builder()
            .add(DataJsonAdapterFactory)

    fun build(): Moshi {
        return builder.build()
    }

}