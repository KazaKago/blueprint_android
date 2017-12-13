package com.kazakago.cleanarchitecture.presentation.extension

import android.content.Context
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.formattedTime(context: Context): String {
    return try {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSSZ", Locale.getDefault())
        return formatter.parse(this).time.formattedTime(context)
    } catch (e: ParseException) {
        ""
    }
}

fun Long.formattedTime(context: Context): String {
    val dateFormat = android.text.format.DateFormat.getDateFormat(context)
    val timeFormat = android.text.format.DateFormat.getTimeFormat(context)
    return dateFormat.format(this) + " " + timeFormat.format(this)
}
