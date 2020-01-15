package com.kazakago.cleanarchitecture.usecase.usecase.weather

import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.repository.weather.WeatherRepository

internal class RequestWeatherUseCaseImpl(
    private val weatherRepository: WeatherRepository
) : RequestWeatherUseCase {

    override suspend fun invoke(cityId: CityId) {
        weatherRepository.request(cityId)
    }

}
