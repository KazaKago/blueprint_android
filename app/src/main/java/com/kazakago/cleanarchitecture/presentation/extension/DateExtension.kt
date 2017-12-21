package com.kazakago.cleanarchitecture.presentation.extension

import android.content.Context
import java.util.*

fun Date.formattedDateTimeText(context: Context): String {
    return formattedDateText(context) + " " + formattedTimeText(context)
}

fun Date.formattedDateText(context: Context): String {
    val dateFormat = android.text.format.DateFormat.getDateFormat(context)
    return dateFormat.format(this)
}

fun Date.formattedTimeText(context: Context): String {
    val timeFormat = android.text.format.DateFormat.getTimeFormat(context)
    return timeFormat.format(this)
}
