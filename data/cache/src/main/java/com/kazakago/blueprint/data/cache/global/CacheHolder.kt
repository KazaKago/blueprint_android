package com.kazakago.blueprint.data.cache.global

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class CacheHolder<T>(
    val value: T,
    val createdAt: Instant = Clock.System.now(),
)
