package com.kazakago.cleanarchitecture.domain.stub.data.repository.weather

import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.weather.Description
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.domain.model.weather.Location
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherRepository
import kotlinx.coroutines.delay
import java.net.URL
import java.util.*

class WeatherRepositoryStub : WeatherRepository {

    override suspend fun find(cityId: CityId): Weather {
        return Weather(
            location = Location(
                area = "area",
                prefecture = "prefecture",
                city = "city"
            ),
            title = "location",
            link = URL("https://wikipedia.org/"),
            publicTime = Date(),
            description = Description(
                text = "description",
                publicTime = Date()
            ),
            forecasts = listOf(
                Forecast(
                    date = Date(),
                    dateLabel = "dateLabel",
                    telop = "telop",
                    imageUrl = URL("https://developer.android.com/images/brand/Android_Robot.png"),
                    maxTemperature = 30.0f,
                    minTemperature = 20.0f
                ),
                Forecast(
                    date = Date(),
                    dateLabel = "dateLabel",
                    telop = "telop",
                    imageUrl = URL("https://developer.android.com/images/brand/Android_Robot.png"),
                    maxTemperature = 30.0f,
                    minTemperature = 20.0f
                ),
                Forecast(
                    date = Date(),
                    dateLabel = "dateLabel",
                    telop = "telop",
                    imageUrl = URL("https://developer.android.com/images/brand/Android_Robot.png"),
                    maxTemperature = 30.0f,
                    minTemperature = 20.0f
                )
            )
        ).apply {
            this.cityId = cityId
        }
    }

    override suspend fun insert(weather: Weather) {
        delay(100)
    }

    override suspend fun delete(weather: Weather) {
        delay(100)
    }

}