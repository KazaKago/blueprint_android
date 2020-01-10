package com.kazakago.cleanarchitecture.model.state

fun <T, R> StoreValue<T>.zip(storeValue: StoreValue<R>): StoreValue<Pair<T, R>> {
    return when (this) {
        is StoreValue.Stored -> this.zip(storeValue)
        is StoreValue.NotStored -> this.zip(storeValue)
    }
}

private fun <T, R> StoreValue.Stored<T>.zip(storeValue: StoreValue<R>): StoreValue<Pair<T, R>> {
    return when (storeValue) {
        is StoreValue.Stored -> StoreValue.Stored(this.value to storeValue.value)
        is StoreValue.NotStored -> StoreValue.NotStored()
    }
}

private fun <T, R> StoreValue.NotStored<T>.zip(storeValue: StoreValue<R>): StoreValue<Pair<T, R>> {
    return when (storeValue) {
        is StoreValue.Stored -> StoreValue.NotStored()
        is StoreValue.NotStored -> StoreValue.NotStored()
    }
}
