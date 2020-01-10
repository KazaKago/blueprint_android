package com.kazakago.cleanarchitecture.usecase.usecase.weather

import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.usecase.output.weather.WeatherOutput
import kotlinx.coroutines.flow.Flow

interface SubscribeWeatherUseCase {

    operator fun invoke(cityId: CityId): Flow<StoreState<WeatherOutput>>

}