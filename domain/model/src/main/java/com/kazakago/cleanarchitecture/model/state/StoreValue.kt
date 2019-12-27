package com.kazakago.cleanarchitecture.model.state

import java.time.LocalDateTime

sealed class StoreValue<T> {
    class NotStored<T> : StoreValue<T>()
    data class Stored<T>(val value: T, val storedTime: LocalDateTime = LocalDateTime.now()) : StoreValue<T>()
}
