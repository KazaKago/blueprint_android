package com.kazakago.cleanarchitecture.model.weather

import io.mockk.mockk
import org.junit.Assert
import org.junit.Test
import java.time.Duration
import java.time.LocalDateTime

class WeatherTest {

    private fun createWeather(createdAt: LocalDateTime): Weather {
        return Weather(
            cityId = mockk(),
            location = mockk(),
            title = "",
            link = mockk(),
            publicTime = mockk(),
            description = mockk(),
            forecasts = mockk(),
            createdAt = createdAt
        )
    }

    @Test
    fun expired() {
        val weather1 = createWeather(LocalDateTime.now() - Duration.ofMinutes(60))
        Assert.assertEquals(true, weather1.isExpired())
        val weather2 = createWeather(LocalDateTime.now() - Duration.ofMinutes(100))
        Assert.assertEquals(true, weather2.isExpired())
        val weather3 = createWeather(LocalDateTime.now() - Duration.ofHours(10))
        Assert.assertEquals(true, weather3.isExpired())
    }

    @Test
    fun notExpired() {
        val weather1 = createWeather(LocalDateTime.now() - Duration.ofMinutes(10))
        Assert.assertEquals(false, weather1.isExpired())
        val weather2 = createWeather(LocalDateTime.now() - Duration.ofMinutes(50))
        Assert.assertEquals(false, weather2.isExpired())
        val weather3 = createWeather(LocalDateTime.now() - Duration.ofMinutes(59))
        Assert.assertEquals(false, weather3.isExpired())
    }

}