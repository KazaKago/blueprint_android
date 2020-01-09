package com.kazakago.cleanarchitecture.repository.weather

import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.weather.Weather
import kotlinx.coroutines.flow.Flow
import java.time.Duration

interface WeatherRepository {

    fun subscribe(cityId: CityId, expired: Duration): Flow<StoreState<Weather>>

}
