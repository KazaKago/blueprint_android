package com.kazakago.cleanarchitecture.memory.memory.weather

import com.kazakago.cleanarchitecture.memory.entity.weather.CityIdEntity
import com.kazakago.cleanarchitecture.memory.memory.MemoryState
import kotlinx.coroutines.channels.ConflatedBroadcastChannel

object WeatherMemory {
    val weatherState = ConflatedBroadcastChannel<Map<CityIdEntity, MemoryState>>(mapOf())
}
