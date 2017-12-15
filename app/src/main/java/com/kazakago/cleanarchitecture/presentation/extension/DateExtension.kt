package com.kazakago.cleanarchitecture.presentation.extension

import android.content.Context
import java.util.*

fun Date.formattedText(context: Context): String {
    val dateFormat = android.text.format.DateFormat.getDateFormat(context)
    val timeFormat = android.text.format.DateFormat.getTimeFormat(context)
    return dateFormat.format(this) + " " + timeFormat.format(this)
}
