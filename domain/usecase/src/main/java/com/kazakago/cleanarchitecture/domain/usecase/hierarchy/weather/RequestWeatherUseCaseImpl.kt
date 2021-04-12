package com.kazakago.cleanarchitecture.domain.usecase.hierarchy.weather

import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId
import com.kazakago.cleanarchitecture.domain.repository.hierarchy.weather.WeatherRepository

internal class RequestWeatherUseCaseImpl(private val weatherRepository: WeatherRepository) : RequestWeatherUseCase {

    override suspend fun invoke(cityId: CityId) {
        weatherRepository.request(cityId)
    }
}
