package com.kazakago.cleanarchitecture.repository.mapper.city

import com.kazakago.cleanarchitecture.memory.memory.MemoryState
import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.state.StoreValue

internal class CityStateMapper {

    fun map(memoryState: MemoryState): StoreState<List<City>> {
        return when (memoryState) {
            is MemoryState.Fixed -> StoreState.Fixed(StoreValue.NotStored())
            is MemoryState.Loading -> StoreState.Loading(StoreValue.NotStored())
            is MemoryState.Error -> StoreState.Error(StoreValue.NotStored(), memoryState.exception)
        }
    }

    fun reverse(storeState: StoreState<List<City>>): MemoryState {
        return when (storeState) {
            is StoreState.Fixed -> MemoryState.Fixed
            is StoreState.Loading -> MemoryState.Loading
            is StoreState.Error -> MemoryState.Error(storeState.exception)
        }
    }

}
