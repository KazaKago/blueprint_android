package com.kazakago.cleanarchitecture.web.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.parseDate(): Date {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatter.parse(this)
}

fun String.parseDateTime(): Date {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSSZ", Locale.getDefault())
    return formatter.parse(this)
}
