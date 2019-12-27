package com.kazakago.cleanarchitecture.usecase.usecase.weather

import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.repository.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow
import java.time.Duration

internal class SubscribeWeatherUseCaseImpl(private val weatherRepository: WeatherRepository) : SubscribeWeatherUseCase {

    override suspend fun invoke(cityId: CityId): Flow<StoreState<Weather>> {
        return weatherRepository.subscribe(cityId, Duration.ofHours(1))
    }

}
