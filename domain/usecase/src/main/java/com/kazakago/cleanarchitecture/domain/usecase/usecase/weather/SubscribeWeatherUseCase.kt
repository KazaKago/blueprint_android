package com.kazakago.cleanarchitecture.domain.usecase.usecase.weather

import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.state.State
import com.kazakago.cleanarchitecture.domain.usecase.output.weather.WeatherOutput
import kotlinx.coroutines.flow.Flow

interface SubscribeWeatherUseCase {

    operator fun invoke(cityId: CityId): Flow<State<WeatherOutput>>

}