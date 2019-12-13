package com.kazakago.cleanarchitecture.view.global.extension

interface StringKey

internal fun <T> T.value(): String where T : Enum<*>, T : StringKey {
    return name
}

internal fun <T> Array<T>.resolve(value: String): T where T : Enum<*>, T : StringKey {
    return first { it.value() == value }
}

internal fun <T> Array<T>.resolveOrNull(value: String): T? where T : Enum<*>, T : StringKey {
    return firstOrNull { it.value() == value }
}

internal fun <T> Array<T>.resolve(value: String, defaultValue: T): T where T : Enum<*>, T : StringKey {
    return resolveOrNull(value) ?: defaultValue
}
