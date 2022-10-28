package com.kazakago.blueprint.data.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

data class Cache<T>(
    val validTime: Duration = 30.minutes,
) {
    private val _data: MutableStateFlow<T?> = MutableStateFlow(null)
    private var savedTime: Instant? = null

    var data: T?
        get() {
            if (isExpire()) _data.value = null
            return _data.value
        }
        set(value) {
            savedTime = Clock.System.now()
            _data.value = value
        }

    var asFlow: Flow<T> = _data.mapNotNull { it }

    private fun isExpire(): Boolean {
        return savedTime?.let {
            (it + validTime) < Clock.System.now()
        } ?: true
    }
}
