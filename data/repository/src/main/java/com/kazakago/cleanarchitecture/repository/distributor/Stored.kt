package com.kazakago.cleanarchitecture.repository.distributor

import java.time.LocalDateTime

internal data class Stored<T>(
    val value: T,
    val storedTime: LocalDateTime = LocalDateTime.now()
)
