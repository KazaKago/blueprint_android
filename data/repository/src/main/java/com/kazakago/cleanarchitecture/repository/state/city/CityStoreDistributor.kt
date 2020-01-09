package com.kazakago.cleanarchitecture.repository.state.city

import android.content.Context
import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.state.StoreValue
import com.kazakago.cleanarchitecture.repository.mapper.city.CityEntityMapper
import com.kazakago.cleanarchitecture.resource.dao.city.CityDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

internal class CityStoreDistributor(context: Context) {

    private val cityDao = CityDao(context)
    private val cityMapper = CityEntityMapper()

    fun subscribe(cityId: CityId): Flow<StoreState<City>> {
        return flow {
            try {
                emit(StoreState.Loading(StoreValue.NotStored()))
                val newValue = fetch().first { city -> city.id == cityId }
                emit(StoreState.Fixed(StoreValue.Stored(newValue)))
            } catch (exception: Exception) {
                emit(StoreState.Error(StoreValue.NotStored(), exception))
            }
        }
    }

    fun subscribeAll(): Flow<StoreState<List<City>>> {
        return flow {
            try {
                emit(StoreState.Loading(StoreValue.NotStored()))
                val newValue = fetch()
                emit(StoreState.Fixed(StoreValue.Stored(newValue)))
            } catch (exception: Exception) {
                emit(StoreState.Error(StoreValue.NotStored(), exception))
            }
        }
    }

    private suspend fun fetch(): List<City> {
        val prefEntityList = withContext(Dispatchers.IO) { cityDao.find() }
        return prefEntityList.flatMap { cityMapper.map(it) }
    }

}