package com.kazakago.cleanarchitecture.domain.repository.hierarchy.weather

import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId
import com.kazakago.cleanarchitecture.domain.model.global.state.State
import com.kazakago.cleanarchitecture.domain.model.hierarchy.weather.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun subscribe(cityId: CityId): Flow<State<Weather>>

    suspend fun request(cityId: CityId)

}
