package com.kazakago.blueprint.domain.usecase.hierarchy.weather

import com.kazakago.blueprint.domain.model.hierarchy.city.CityId
import com.kazakago.blueprint.domain.repository.hierarchy.weather.WeatherRepository

internal class RequestWeatherUseCaseImpl(private val weatherRepository: WeatherRepository) : RequestWeatherUseCase {

    override suspend fun invoke(cityId: CityId) {
        weatherRepository.request(cityId)
    }
}
