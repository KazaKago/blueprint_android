package com.kazakago.cleanarchitecture.data.repository.hierarchy.city

import android.content.Context
import com.kazakago.cleanarchitecture.data.repository.global.dispatcher.FlowDispatcher
import com.kazakago.cleanarchitecture.data.repository.mapper.city.CityEntityMapper
import com.kazakago.cleanarchitecture.data.resource.hierarchy.city.CityDao
import com.kazakago.cleanarchitecture.domain.model.global.state.State
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.City
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId
import com.kazakago.cleanarchitecture.domain.repository.hierarchy.city.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class CityRepositoryImpl(context: Context) : CityRepository {

    private val cityDao = CityDao(context)
    private val cityMapper = CityEntityMapper()

    override fun subscribe(cityId: CityId): Flow<State<City>> {
        return FlowDispatcher(
            fetch = { get(cityId) }
        ).subscribe()
    }

    override fun subscribeAll(): Flow<State<List<City>>> {
        return FlowDispatcher(
            fetch = { getAll() }
        ).subscribe()
    }

    private suspend fun get(cityId: CityId): City {
        return getAll().first { it.id == cityId }
    }

    private suspend fun getAll(): List<City> {
        val prefEntityList = withContext(Dispatchers.IO) { cityDao.find() }
        return prefEntityList.flatMap { cityMapper.map(it) }
    }

}
