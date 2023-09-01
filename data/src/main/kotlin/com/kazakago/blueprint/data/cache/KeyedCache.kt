package com.kazakago.blueprint.data.cache

import kotlin.time.Duration

data class KeyedCache<KEY, DATA>(
    val validTime: Duration? = null,
) {
    private val _dataMap: MutableMap<KEY, Cache<DATA>> = mutableMapOf()

    operator fun get(key: KEY): Cache<DATA> {
        return _dataMap.getOrPut(key)
    }

    private fun MutableMap<KEY, Cache<DATA>>.getOrPut(key: KEY): Cache<DATA> {
        val storedCache = this[key]
        return if (storedCache != null) {
            storedCache
        } else {
            val newCache = if (validTime != null) {
                Cache<DATA>(validTime)
            } else {
                Cache()
            }
            this[key] = newCache
            newCache
        }
    }
}