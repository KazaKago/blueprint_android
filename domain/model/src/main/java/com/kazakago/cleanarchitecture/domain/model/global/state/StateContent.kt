package com.kazakago.cleanarchitecture.domain.model.global.state

sealed class StateContent<out T> {
    class NotStored<out T> : StateContent<T>()
    data class Stored<out T>(val rawContent: T) : StateContent<T>()
}
