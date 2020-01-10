package com.kazakago.cleanarchitecture.model.state

fun <T, R, W> StoreState<T>.zip(state: StoreState<R>, transform: (value1: T, value2: R) -> W): StoreState<W> {
    return when (this) {
        is StoreState.Fixed -> this.zip(state, transform)
        is StoreState.Loading -> this.zip(state, transform)
        is StoreState.Error -> this.zip(state, transform)
    }
}

private fun <T, R, W> StoreState.Fixed<T>.zip(state: StoreState<R>, transform: (value1: T, value2: R) -> W): StoreState<W> {
    return when (state) {
        is StoreState.Fixed -> StoreState.Fixed(value.zip(state.value, transform))
        is StoreState.Loading -> StoreState.Loading(value.zip(state.value, transform))
        is StoreState.Error -> StoreState.Error(value.zip(state.value, transform), state.exception)
    }
}

private fun <T, R, W> StoreState.Loading<T>.zip(state: StoreState<R>, transform: (value1: T, value2: R) -> W): StoreState<W> {
    return when (state) {
        is StoreState.Fixed -> StoreState.Loading(value.zip(state.value, transform))
        is StoreState.Loading -> StoreState.Loading(value.zip(state.value, transform))
        is StoreState.Error -> StoreState.Error(value.zip(state.value, transform), state.exception)
    }
}

private fun <T, R, W> StoreState.Error<T>.zip(state: StoreState<R>, transform: (value1: T, value2: R) -> W): StoreState<W> {
    return when (state) {
        is StoreState.Fixed -> StoreState.Error(value.zip(state.value, transform), this.exception)
        is StoreState.Loading -> StoreState.Error(value.zip(state.value, transform), this.exception)
        is StoreState.Error -> StoreState.Error(value.zip(state.value, transform), this.exception)
    }
}
