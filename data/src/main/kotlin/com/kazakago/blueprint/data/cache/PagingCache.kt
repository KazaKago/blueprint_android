package com.kazakago.blueprint.data.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

data class PagingCache<DATA, NEXT_PAGE>(
    val validTime: Duration = 30.minutes,
) {
    data class Result<DATA, NEXT_PAGE>(
        val newData: List<DATA>?,
        val newNextPage: NEXT_PAGE?,
    )

    private val dataFlow: MutableStateFlow<List<DATA>?> = MutableStateFlow(null)
    private var savedTime: Instant? = null
    private var nextPage: NEXT_PAGE? = null

    val data: List<DATA>? = dataFlow.value
    val asFlow: Flow<List<DATA>> = dataFlow.filterNotNull()

    suspend fun fetch(force: Boolean, block: suspend () -> Result<DATA, NEXT_PAGE?>) {
        if (isExpire() || force) {
            val (newData, newNextKey) = block()
            savedTime = Clock.System.now()
            dataFlow.value = newData
            nextPage = newNextKey
        }
    }

    suspend fun fetchNext(block: suspend (nextPage: NEXT_PAGE?) -> Result<DATA, NEXT_PAGE?>) {
        if (data != null) {
            val (newData, newNextKey) = block(nextPage)
            dataFlow.value = data + (newData ?: emptyList())
            nextPage = newNextKey
        }
    }

    private fun isExpire(): Boolean {
        return savedTime?.let {
            (it + validTime) < Clock.System.now()
        } ?: true
    }
}
