package com.kazakago.cleanarchitecture.domain.usecase.usecase.weather

import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherRepository

internal class RequestWeatherUseCaseImpl(
    private val weatherRepository: WeatherRepository
) : RequestWeatherUseCase {

    override suspend fun invoke(cityId: CityId) {
        weatherRepository.request(cityId)
    }

}
