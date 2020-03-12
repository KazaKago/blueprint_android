package com.kazakago.cleanarchitecture.data.resource.hierarchy.city

import android.content.Context
import com.kazakago.cleanarchitecture.data.resource.R
import com.kazakago.cleanarchitecture.data.resource.entity.city.PrefEntity
import com.kazakago.cleanarchitecture.data.resource.global.MoshiBuilder
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CityDao(private val context: Context) {

    suspend fun getAll(): List<PrefEntity> {
        return withContext(Dispatchers.IO) {
            val jsonStr = readJsonStr()
            parseJson(jsonStr)
        }
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
