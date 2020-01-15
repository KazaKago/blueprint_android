package com.kazakago.cleanarchitecture.domain.model.weather

import java.io.Serializable
import java.net.URL
import java.time.LocalDate

data class Forecast(
    var date: LocalDate,
    var dateLabel: String,
    var telop: String,
    var imageUrl: URL,
    var maxTemperature: Float?,
    var minTemperature: Float?
) : Serializable