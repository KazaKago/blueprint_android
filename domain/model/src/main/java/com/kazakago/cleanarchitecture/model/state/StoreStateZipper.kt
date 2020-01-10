package com.kazakago.cleanarchitecture.model.state

fun <T, R> StoreState<T>.zip(state: StoreState<R>): StoreState<Pair<T, R>> {
    return when (this) {
        is StoreState.Fixed -> this.zip(state)
        is StoreState.Loading -> this.zip(state)
        is StoreState.Error -> this.zip(state)
    }
}

private fun <T, R> StoreState.Fixed<T>.zip(state: StoreState<R>): StoreState<Pair<T, R>> {
    return when (state) {
        is StoreState.Fixed -> StoreState.Fixed(value.zip(state.value))
        is StoreState.Loading -> StoreState.Loading(value.zip(state.value))
        is StoreState.Error -> StoreState.Error(value.zip(state.value), state.exception)
    }
}

private fun <T, R> StoreState.Loading<T>.zip(state: StoreState<R>): StoreState<Pair<T, R>> {
    return when (state) {
        is StoreState.Fixed -> StoreState.Loading(value.zip(state.value))
        is StoreState.Loading -> StoreState.Loading(value.zip(state.value))
        is StoreState.Error -> StoreState.Error(value.zip(state.value), state.exception)
    }
}

private fun <T, R> StoreState.Error<T>.zip(state: StoreState<R>): StoreState<Pair<T, R>> {
    return when (state) {
        is StoreState.Fixed -> StoreState.Error(value.zip(state.value), this.exception)
        is StoreState.Loading -> StoreState.Error(value.zip(state.value), this.exception)
        is StoreState.Error -> StoreState.Error(value.zip(state.value), this.exception)
    }
}
