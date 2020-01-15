package com.kazakago.cleanarchitecture.usecase.usecase.weather

import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.State
import com.kazakago.cleanarchitecture.model.state.combineState
import com.kazakago.cleanarchitecture.repository.city.CityRepository
import com.kazakago.cleanarchitecture.repository.weather.WeatherRepository
import com.kazakago.cleanarchitecture.usecase.output.weather.WeatherOutput
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
