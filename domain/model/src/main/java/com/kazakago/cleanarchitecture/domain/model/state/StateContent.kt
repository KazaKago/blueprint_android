package com.kazakago.cleanarchitecture.domain.model.state

sealed class StateContent<T> {
    class NotStored<T> : StateContent<T>()
    data class Stored<T>(val rawContent: T) : StateContent<T>()
}
