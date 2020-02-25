package com.kazakago.cleanarchitecture.domain.usecase.hierarchy.weather

import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId
import com.kazakago.cleanarchitecture.domain.model.global.state.State
import com.kazakago.cleanarchitecture.domain.usecase.output.weather.WeatherOutput
import kotlinx.coroutines.flow.Flow

interface SubscribeWeatherUseCase {

    operator fun invoke(cityId: CityId): Flow<State<WeatherOutput>>

}