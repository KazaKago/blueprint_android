package com.kazakago.blueprint.data.cache

import kotlin.time.Duration

data class KeyedCache<T, R>(
    val validTime: Duration? = null,
) {
    private val _dataMap: MutableMap<T, Cache<R>> = mutableMapOf()

    operator fun get(key: T): Cache<R> {
        return _dataMap.getOrPut(key)
    }

    operator fun set(key: T, value: R) {
        _dataMap.getOrPut(key).data = value
    }

    private fun <T, R> MutableMap<T, Cache<R>>.getOrPut(key: T): Cache<R> {
        val storedCache = this[key]
        return if (storedCache != null) {
            storedCache
        } else {
            val newCache = if (validTime != null) {
                Cache<R>(validTime)
            } else {
                Cache()
            }
            this[key] = newCache
            newCache
        }
    }
}
