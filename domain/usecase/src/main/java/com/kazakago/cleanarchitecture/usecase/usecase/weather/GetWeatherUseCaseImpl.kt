package com.kazakago.cleanarchitecture.usecase.usecase.weather

import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.repository.weather.WeatherRepository
import java.time.Duration

internal class GetWeatherUseCaseImpl(private val weatherRepository: WeatherRepository) : GetWeatherUseCase {

    override suspend fun invoke(cityId: CityId): Weather {
        return weatherRepository.get(cityId, Duration.ofHours(1))
    }

}
