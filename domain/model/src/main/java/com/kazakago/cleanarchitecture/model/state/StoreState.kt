package com.kazakago.cleanarchitecture.model.state

sealed class StoreState<T>(
    val value: StoreValue<T>
) {
    class Fixed<T>(value: StoreValue<T>) : StoreState<T>(value)
    class Loading<T>(value: StoreValue<T>) : StoreState<T>(value)
    class Error<T>(value: StoreValue<T>, val exception: Exception) : StoreState<T>(value)
}
