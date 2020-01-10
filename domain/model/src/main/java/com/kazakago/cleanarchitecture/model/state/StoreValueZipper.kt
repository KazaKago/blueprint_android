package com.kazakago.cleanarchitecture.model.state

fun <T, R, W> StoreValue<T>.zip(storeValue: StoreValue<R>, transform: (value1: T, value2: R) -> W): StoreValue<W> {
    return when (this) {
        is StoreValue.Stored -> this.zip(storeValue, transform)
        is StoreValue.NotStored -> this.zip(storeValue, transform)
    }
}

private fun <T, R, W> StoreValue.Stored<T>.zip(storeValue: StoreValue<R>, transform: (value1: T, value2: R) -> W): StoreValue<W> {
    return when (storeValue) {
        is StoreValue.Stored -> StoreValue.Stored(transform(value, storeValue.value))
        is StoreValue.NotStored -> StoreValue.NotStored()
    }
}

private fun <T, R, W> StoreValue.NotStored<T>.zip(storeValue: StoreValue<R>, transform: (value1: T, value2: R) -> W): StoreValue<W> {
    return when (storeValue) {
        is StoreValue.Stored -> StoreValue.NotStored()
        is StoreValue.NotStored -> StoreValue.NotStored()
    }
}
