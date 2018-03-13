package com.kazakago.cleanarchitecture.web.parser

import com.squareup.moshi.Moshi

class MoshiBuilder {

    private val builder = Moshi.Builder()
            .add(WebJsonAdapterFactory.INSTANCE)

    fun build(): Moshi {
        return builder.build()
    }

}