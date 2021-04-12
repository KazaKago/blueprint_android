package com.kazakago.blueprint.domain.model.hierarchy.weather

import java.io.Serializable
import java.net.URL
import java.time.LocalDate

data class Forecast(
    val date: LocalDate,
    val dateLabel: String,
    val telop: String,
    val imageUrl: URL,
    val maxTemperature: Float?,
    val minTemperature: Float?
) : Serializable
