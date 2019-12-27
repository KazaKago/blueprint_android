package com.kazakago.cleanarchitecture.repository.mapper.weather

import com.kazakago.cleanarchitecture.database.entity.weather.DescriptionEntity
import com.kazakago.cleanarchitecture.database.entity.weather.ForecastEntity
import com.kazakago.cleanarchitecture.database.entity.weather.LocationEntity
import com.kazakago.cleanarchitecture.database.entity.weather.WeatherEntity
import com.kazakago.cleanarchitecture.memory.memory.MemoryState
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.state.StoreValue
import com.kazakago.cleanarchitecture.model.weather.Weather

internal class WeatherStateMapper(
    private val weatherEntityMapper: WeatherEntityMapper
) {

    fun map(memoryState: MemoryState, weather: WeatherEntity?, location: LocationEntity?, description: DescriptionEntity?, forecasts: List<ForecastEntity>?): StoreState<Weather> {
        return when (memoryState) {
            is MemoryState.Fixed -> StoreState.Fixed(mapValue(weather, location, description, forecasts))
            is MemoryState.Loading -> StoreState.Loading(mapValue(weather, location, description, forecasts))
            is MemoryState.Error -> StoreState.Error(mapValue(weather, location, description, forecasts), memoryState.exception)
        }
    }

    private fun mapValue(weather: WeatherEntity?, location: LocationEntity?, description: DescriptionEntity?, forecasts: List<ForecastEntity>?): StoreValue<Weather> {
        return if (weather != null && location != null && description != null && forecasts != null) {
            val mappingResult = weatherEntityMapper.map(weather, location, description, forecasts)
            StoreValue.Stored(mappingResult.weather, mappingResult.createdAt)
        } else {
            StoreValue.NotStored()
        }
    }

    fun reverse(storeState: StoreState<Weather>): ReverseMappingResult {
        val state = when (storeState) {
            is StoreState.Fixed -> MemoryState.Fixed
            is StoreState.Loading -> MemoryState.Loading
            is StoreState.Error -> MemoryState.Error(storeState.exception)
        }
        val value = when (val storeValue = storeState.value) {
            is StoreValue.Stored -> weatherEntityMapper.reverse(storeValue.value, storeValue.storedTime)
            is StoreValue.NotStored -> null
        }
        return ReverseMappingResult(
            memoryState = state,
            weatherEntity = value?.weatherEntity,
            locationEntity = value?.locationEntity,
            descriptionEntity = value?.descriptionEntity,
            forecastEntities = value?.forecastEntities
        )
    }

    data class ReverseMappingResult(
        val memoryState: MemoryState,
        val weatherEntity: WeatherEntity?,
        val locationEntity: LocationEntity?,
        val descriptionEntity: DescriptionEntity?,
        val forecastEntities: List<ForecastEntity>?
    )

}