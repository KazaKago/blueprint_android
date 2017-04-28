package com.kazakago.cleanarchitecture.data.dao

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kazakago.cleanarchitecture.data.entity.city.PrefEntity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * City Data Access Object.
 *
 * Created by weath on 2016/06/14.
 */
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

    private fun parseJson(jsonStr: String?): List<PrefEntity> {
        val gson = Gson()
        val entityList = gson.fromJson<List<PrefEntity>>(jsonStr, object : TypeToken<List<PrefEntity>>() {
        }.type)

        return entityList
    }

}
