package com.kazakago.cleanarchitecture.domain.usecase.usecase.weather

import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.state.State
import com.kazakago.cleanarchitecture.domain.model.state.combineState
import com.kazakago.cleanarchitecture.domain.repository.city.CityRepository
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherRepository
import com.kazakago.cleanarchitecture.domain.usecase.output.weather.WeatherOutput
import kotlinx.coroutines.flow.Flow

internal class SubscribeWeatherUseCaseImpl(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository
) : SubscribeWeatherUseCase {

    override fun invoke(cityId: CityId): Flow<State<WeatherOutput>> {
        val cityFlow = cityRepository.subscribe(cityId)
        val weatherFlow = weatherRepository.subscribe(cityId)
        return cityFlow.combineState(weatherFlow) { city, weather ->
            WeatherOutput(city, weather)
        }
    }

}
