package com.kazakago.cleanarchitecture.data.database.global

import androidx.room.TypeConverter
import java.time.LocalDateTime

class DateTimeConverter {

    @TypeConverter
    fun fromTimeString(value: String?): LocalDateTime? {
        return if (value == null) null else LocalDateTime.parse(value)
    }

    @TypeConverter
    fun toTimeString(date: LocalDateTime?): String? {
        return date?.toString()
    }

}