package com.kazakago.cleanarchitecture.data.parser

import com.squareup.moshi.Moshi

class MoshiBuilder {

    private val builder = Moshi.Builder()

    fun build(): Moshi {
        return builder.build()
    }

}