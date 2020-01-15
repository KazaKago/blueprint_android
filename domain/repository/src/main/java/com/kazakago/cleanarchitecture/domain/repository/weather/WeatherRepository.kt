package com.kazakago.cleanarchitecture.domain.repository.weather

import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.state.State
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun subscribe(cityId: CityId): Flow<State<Weather>>

    suspend fun request(cityId: CityId)

}
