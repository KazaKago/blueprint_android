package com.kazakago.cleanarchitecture.presentation.view.global.extension

import android.content.Context
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.*

internal fun LocalDateTime.formattedText(context: Context): String {
    return toLocalDate().formattedText(context) + " " + toLocalTime().formattedText(context)
}

internal fun LocalDate.formattedText(context: Context): String {
    val date = Date.from(atStartOfDay(ZoneId.systemDefault()).toInstant())
    val formatter = android.text.format.DateFormat.getDateFormat(context)
    return formatter.format(date)
}

internal fun LocalTime.formattedText(context: Context): String {
    val date = Date.from(atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant())
    val formatter = android.text.format.DateFormat.getTimeFormat(context)
    return formatter.format(date)
}
