package com.kazakago.cleanarchitecture.data.memory.hierarchy.weather

import com.kazakago.cleanarchitecture.data.memory.entity.weather.CityIdEntity
import com.kazakago.cleanarchitecture.data.memory.global.DataState
import kotlinx.coroutines.channels.ConflatedBroadcastChannel

object WeatherMemory {
    val weatherState = ConflatedBroadcastChannel<Map<CityIdEntity, DataState>>(mapOf())
}
