package com.kazakago.blueprint.data.cache

import kotlin.time.Duration

data class KeyedPagingCache<KEY, DATA, NEXT_PAGE>(
    val validTime: Duration? = null,
) {
    private val _dataMap: MutableMap<KEY, PagingCache<DATA, NEXT_PAGE>> = mutableMapOf()

    operator fun get(key: KEY): PagingCache<DATA, NEXT_PAGE> {
        return _dataMap.getOrPut(key)
    }

    private fun MutableMap<KEY, PagingCache<DATA, NEXT_PAGE>>.getOrPut(key: KEY): PagingCache<DATA, NEXT_PAGE> {
        val storedCache = this[key]
        return if (storedCache != null) {
            storedCache
        } else {
            val newCache = if (validTime != null) {
                PagingCache<DATA, NEXT_PAGE>(validTime)
            } else {
                PagingCache()
            }
            this[key] = newCache
            newCache
        }
    }
}
