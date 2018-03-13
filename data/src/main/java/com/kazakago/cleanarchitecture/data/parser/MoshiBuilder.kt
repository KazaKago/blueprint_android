package com.kazakago.cleanarchitecture.data.parser

import com.squareup.moshi.Moshi

class MoshiBuilder {

    private val builder = Moshi.Builder()
            .add(DataJsonAdapterFactory.INSTANCE)

    fun build(): Moshi {
        return builder.build()
    }

}