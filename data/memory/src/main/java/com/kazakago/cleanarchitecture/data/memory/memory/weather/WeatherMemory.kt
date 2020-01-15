package com.kazakago.cleanarchitecture.data.memory.memory.weather

import com.kazakago.cleanarchitecture.data.memory.entity.weather.CityIdEntity
import com.kazakago.cleanarchitecture.data.memory.memory.DataState
import kotlinx.coroutines.channels.ConflatedBroadcastChannel

object WeatherMemory {
    val weatherState = ConflatedBroadcastChannel<Map<CityIdEntity, DataState>>(mapOf())
}
