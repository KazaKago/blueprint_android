package com.kazakago.cleanarchitecture.data.file.dao.city

import android.content.Context
import com.kazakago.cleanarchitecture.data.file.entity.city.PrefEntity
import com.kazakago.cleanarchitecture.data.parser.MoshiBuilder
import com.squareup.moshi.Types
import io.reactivex.Single
import java.io.BufferedReader
import java.io.InputStreamReader

class CityDao(private val context: Context) {

    fun find(): Single<List<PrefEntity>> {
        return readJson()
                .flatMap {
                    parseJson(it)
                }
    }

    private fun readJson(): Single<String> {
        return Single.create {
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
            it.onSuccess(text)
        }
    }

    private fun parseJson(jsonStr: String): Single<List<PrefEntity>> {
        return Single.create {
            val moshi = MoshiBuilder().build()
            val type = Types.newParameterizedType(List::class.java, PrefEntity::class.java)
            val adapter = moshi.adapter<List<PrefEntity>>(type)
            it.onSuccess(adapter.fromJson(jsonStr).orEmpty())
        }
    }

}
