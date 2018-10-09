package com.kazakago.cleanarchitecture.presentation.extension

interface StringKey

fun <T> T.value(): String where T : Enum<*>, T : StringKey {
    return name
}

fun <T> Array<T>.resolve(value: String): T? where T : Enum<*>, T : StringKey {
    return firstOrNull { it.value() == value }
}

fun <T> Array<T>.resolve(value: String, defaultValue: T): T where T : Enum<*>, T : StringKey {
    return resolve(value) ?: defaultValue
}
