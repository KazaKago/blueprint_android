package com.kazakago.cleanarchitecture.web.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.parseTime(): Date {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSSZ", Locale.getDefault())
    return formatter.parse(this)
}
