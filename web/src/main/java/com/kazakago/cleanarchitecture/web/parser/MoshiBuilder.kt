package com.kazakago.cleanarchitecture.web.parser

import com.squareup.moshi.Moshi

class MoshiBuilder {

    private val builder = Moshi.Builder()
            .add(WebJsonAdapterFactory)

    fun build(): Moshi {
        return builder.build()
    }

}