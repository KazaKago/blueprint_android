package com.kazakago.cleanarchitecture.presentation.extension

interface IntKey

fun <T> T.value(): Int where T : Enum<*>, T : IntKey {
    return ordinal + 1000
}

fun <T> Array<T>.resolve(value: Int): T where T : Enum<*>, T : IntKey {
    return first { it.value() == value }
}

fun <T> Array<T>.resolveOrNull(value: Int): T? where T : Enum<*>, T : IntKey {
    return firstOrNull { it.value() == value }
}

fun <T> Array<T>.resolve(value: Int, defaultValue: T): T where T : Enum<*>, T : IntKey {
    return resolveOrNull(value) ?: defaultValue
}
