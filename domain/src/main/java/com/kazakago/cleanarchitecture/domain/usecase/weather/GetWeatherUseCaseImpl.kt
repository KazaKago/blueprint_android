package com.kazakago.cleanarchitecture.domain.usecase.weather

import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherApiRepository
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherRepository

class GetWeatherUseCaseImpl(private val weatherApiRepository: WeatherApiRepository, private val weatherRepository: WeatherRepository) : GetWeatherUseCase {

    override fun execute(input: CityId): Weather {
        return try {
            val weather = weatherApiRepository.fetch(input)
            weather.cityId = input
            weatherRepository.insert(weather)
            weather
        } catch (exception: Exception) {
            try {
                weatherRepository.find(input)
            } catch (_: Exception) {
                throw exception
            }
        }
    }

}
