package com.kazakago.cleanarchitecture.presentation.extension

import io.reactivex.exceptions.CompositeException

fun Throwable.compositeLocalizedMessage(): String {
    return compositeLocalizedMessage("\n")
}

fun Throwable.compositeLocalizedMessage(separator: String): String {
    return if (this is CompositeException) {
        exceptions.joinToString(separator) { it.localizedMessage }
    } else {
        localizedMessage
    }
}