package com.kazakago.cleanarchitecture.usecase.usecase.weather

import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.weather.Weather
import kotlinx.coroutines.flow.Flow

interface SubscribeWeatherUseCase {

    suspend operator fun invoke(cityId: CityId): Flow<StoreState<Weather>>

}