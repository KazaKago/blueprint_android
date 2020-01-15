package com.kazakago.cleanarchitecture.data.repository.extension

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal fun String.parseDate(): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return LocalDate.parse(this, formatter)
}

internal fun String.parseDateTime(): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
    return LocalDateTime.parse(this, formatter)
}
