package com.kazakago.cleanarchitecture.domain.model.state

sealed class State<T>(
    val content: StateContent<T>
) {
    class Fixed<T>(content: StateContent<T>) : State<T>(content)
    class Loading<T>(content: StateContent<T>) : State<T>(content)
    class Error<T>(content: StateContent<T>, val exception: Exception) : State<T>(content)
}
