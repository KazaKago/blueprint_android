package com.kazakago.cleanarchitecture.domain.stub.web.repository.weather

import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.weather.Description
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.domain.model.weather.Location
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherApiRepository
import java.net.URL
import java.util.*

class WeatherApiRepositoryStub : WeatherApiRepository {

    override suspend fun fetch(cityId: CityId): Weather {
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

}