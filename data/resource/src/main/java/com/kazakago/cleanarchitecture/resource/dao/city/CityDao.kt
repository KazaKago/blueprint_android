package com.kazakago.cleanarchitecture.resource.dao.city

import android.content.Context
import com.kazakago.cleanarchitecture.resource.R
import com.kazakago.cleanarchitecture.resource.entity.city.PrefEntity
import com.kazakago.cleanarchitecture.resource.parser.MoshiBuilder
import com.squareup.moshi.Types

class CityDao(private val context: Context) {

    fun find(): List<PrefEntity> {
        val jsonStr = readJsonStr()
        return parseJson(jsonStr)
    }

    private fun readJsonStr(): String {
        val inputStream = context.resources.openRawResource(R.raw.city)
        return inputStream.bufferedReader().use { it.readText() }
    }

    private fun parseJson(jsonStr: String): List<PrefEntity> {
        val moshi = MoshiBuilder().build()
        val type = Types.newParameterizedType(List::class.java, PrefEntity::class.java)
        val adapter = moshi.adapter<List<PrefEntity>>(type)
        return adapter.fromJson(jsonStr).orEmpty()
    }

}
