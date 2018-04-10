package com.kazakago.cleanarchitecture.domain.model.weather

import java.io.Serializable
import java.net.URL
import java.util.*

data class Forecast(
        var date: Date,
        var dateLabel: String,
        var telop: String,
        var imageUrl: URL,
        var maxTemperature: Float?,
        var minTemperature: Float?
) : Serializable