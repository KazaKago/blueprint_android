package com.kazakago.blueprint.data.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

data class Cache<DATA>(
    val validTime: Duration = 30.minutes,
) {
    private var dataFlow: MutableStateFlow<DATA?> = MutableStateFlow(null)
    private var savedTime: Instant? = null

    val data: DATA? = dataFlow.value
    val asFlow: Flow<DATA> = dataFlow.filterNotNull()

    suspend fun fetch(force: Boolean, block: suspend () -> DATA) {
        if (isExpire() || force) {
            val newData = block()
            savedTime = Clock.System.now()
            dataFlow.value = newData
        }
    }

    private fun isExpire(): Boolean {
        return savedTime?.let {
            (it + validTime) < Clock.System.now()
        } ?: true
    }
}
