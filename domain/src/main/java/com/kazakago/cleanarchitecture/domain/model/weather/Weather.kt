package com.kazakago.cleanarchitecture.domain.model.weather

import com.kazakago.cleanarchitecture.domain.model.city.CityId
import java.io.Serializable
import java.net.URL
import java.util.*

data class Weather(
    val location: Location,
    val title: String,
    val link: URL,
    val publicTime: Date,
    val description: Description,
    val forecasts: List<Forecast>
) : Serializable {
    lateinit var cityId: CityId
}
