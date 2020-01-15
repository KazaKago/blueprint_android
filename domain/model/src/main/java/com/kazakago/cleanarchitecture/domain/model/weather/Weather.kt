package com.kazakago.cleanarchitecture.domain.model.weather

import com.kazakago.cleanarchitecture.domain.model.city.CityId
import java.io.Serializable
import java.net.URL
import java.time.Duration
import java.time.LocalDateTime

data class Weather(
    val cityId: CityId,
    val location: Location,
    val title: String,
    val link: URL,
    val publicTime: LocalDateTime,
    val description: Description,
    val forecasts: List<Forecast>,
    val createdAt: LocalDateTime = LocalDateTime.now()
) : Serializable {

    companion object {
        private val EXPIRED = Duration.ofHours(1)
    }

    fun isExpired(): Boolean {
        return ((createdAt + EXPIRED) <= LocalDateTime.now())
    }

}
