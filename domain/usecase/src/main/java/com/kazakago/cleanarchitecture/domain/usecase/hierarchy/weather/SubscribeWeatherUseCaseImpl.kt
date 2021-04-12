package com.kazakago.cleanarchitecture.domain.usecase.hierarchy.weather

import com.kazakago.cleanarchitecture.domain.model.global.state.State
import com.kazakago.cleanarchitecture.domain.model.global.state.combineState
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId
import com.kazakago.cleanarchitecture.domain.repository.hierarchy.city.CityRepository
import com.kazakago.cleanarchitecture.domain.repository.hierarchy.weather.WeatherRepository
import com.kazakago.cleanarchitecture.domain.usecase.output.weather.WeatherOutput
import kotlinx.coroutines.flow.Flow

internal class SubscribeWeatherUseCaseImpl(private val cityRepository: CityRepository, private val weatherRepository: WeatherRepository) : SubscribeWeatherUseCase {

    override fun invoke(cityId: CityId): Flow<State<WeatherOutput>> {
        val cityFlow = cityRepository.subscribe(cityId)
        val weatherFlow = weatherRepository.subscribe(cityId)
        return cityFlow.combineState(weatherFlow) { city, weather ->
            WeatherOutput(city, weather)
        }
    }
}
