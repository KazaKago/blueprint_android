package com.kazakago.cleanarchitecture.repository.weather

import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.State
import com.kazakago.cleanarchitecture.model.weather.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun subscribe(cityId: CityId): Flow<State<Weather>>

    suspend fun request(cityId: CityId)

}
