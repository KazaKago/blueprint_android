package com.kazakago.cleanarchitecture.repository.distributor

import java.time.Duration
import java.time.LocalDateTime

internal class Distributor(private val validTime: Duration) {

    suspend fun <T> execute(load: (suspend () -> Stored<T>?), save: (suspend (content: Stored<T>) -> Unit), fetch: (suspend () -> T)): T {
        val storedContent = load()
        return if (storedContent != null && LocalDateTime.now() < (storedContent.storedTime + validTime)) {
            storedContent.value
        } else {
            val newContent = fetch()
            save(Stored(newContent))
            newContent
        }
    }

}
