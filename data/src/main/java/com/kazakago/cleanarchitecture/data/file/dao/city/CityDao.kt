package com.kazakago.cleanarchitecture.data.file.dao.city

import android.content.Context
import com.kazakago.cleanarchitecture.data.file.entity.city.PrefEntity
import com.kazakago.cleanarchitecture.data.parser.MoshiBuilder
import com.squareup.moshi.Types
import java.io.BufferedReader
import java.io.InputStreamReader

class CityDao(private val context: Context) {

    fun find(): List<PrefEntity> {
        val jsonStr = readJson()
        return parseJson(jsonStr)
    }

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
        val moshi = MoshiBuilder().build()
        val type = Types.newParameterizedType(List::class.java, PrefEntity::class.java)
        val adapter = moshi.adapter<List<PrefEntity>>(type)
        return adapter.fromJson(jsonStr).orEmpty()
    }

}
