package com.kazakago.cleanarchitecture.usecase.usecase.weather

import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.repository.weather.WeatherApiRepository
import com.kazakago.cleanarchitecture.repository.weather.WeatherRepository

internal class GetWeatherUseCaseImpl(private val weatherApiRepository: WeatherApiRepository, private val weatherRepository: WeatherRepository) : GetWeatherUseCase {

    override suspend fun invoke(cityId: CityId): Weather {
        return try {
            val weather = weatherApiRepository.fetch(cityId)
            weather.cityId = cityId
            weatherRepository.insert(weather)
            weather
        } catch (exception: Exception) {
            try {
                weatherRepository.find(cityId)
            } catch (_: Exception) {
                throw exception
            }
        }
    }

}
