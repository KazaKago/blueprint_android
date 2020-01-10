package com.kazakago.cleanarchitecture.usecase.usecase.weather

import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.state.zip
import com.kazakago.cleanarchitecture.repository.city.CityRepository
import com.kazakago.cleanarchitecture.repository.weather.WeatherRepository
import com.kazakago.cleanarchitecture.usecase.output.weather.WeatherOutput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.time.Duration

internal class SubscribeWeatherUseCaseImpl(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository
) : SubscribeWeatherUseCase {

    override fun invoke(cityId: CityId): Flow<StoreState<WeatherOutput>> {
        val cityFlow = cityRepository.subscribe(cityId)
        val weatherFlow = weatherRepository.subscribe(cityId, Duration.ofHours(1))
        return cityFlow.combine(weatherFlow) { cityState, weatherState ->
            cityState.zip(weatherState) { city, weather ->
                WeatherOutput(city, weather)
            }
        }
    }

}
