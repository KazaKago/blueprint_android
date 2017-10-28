package com.kazakago.cleanarchitecture.data.dao

import android.content.Context
import com.kazakago.cleanarchitecture.data.entity.city.PrefEntity
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class CityDao(private val context: Context) {

    @Throws(IOException::class)
    fun find(): List<PrefEntity> {
        val cityJson = readJson()
        return parseJson(jsonStr = cityJson)
    }

    @Throws(IOException::class)
    private fun readJson(): String {
        var text = ""
        context.assets.open("json/city.json").use {
            BufferedReader(InputStreamReader(it)).use {
                var str = it.readLine()
                while (str != null) {
                    text += str + "\n"
                    str = it.readLine()
                }
            }
        }
        return text
    }

    private fun parseJson(jsonStr: String): List<PrefEntity> {
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        val type = Types.newParameterizedType(List::class.java, PrefEntity::class.java)
        val adapter = moshi.adapter<List<PrefEntity>>(type)
        return adapter.fromJson(jsonStr).orEmpty()
    }

}
