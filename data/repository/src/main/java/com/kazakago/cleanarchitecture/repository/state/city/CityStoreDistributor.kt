package com.kazakago.cleanarchitecture.repository.state.city

import android.content.Context
import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.repository.mapper.city.CityEntityMapper
import com.kazakago.cleanarchitecture.resource.dao.city.CityDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class CityStoreDistributor(context: Context) {

    private val cityDao = CityDao(context)
    private val cityMapper = CityEntityMapper()

    suspend fun get(cityId: CityId): City {
        return getAll().first { it.id == cityId }
    }

    suspend fun getAll(): List<City> {
        val prefEntityList = withContext(Dispatchers.IO) { cityDao.find() }
        return prefEntityList.flatMap { cityMapper.map(it) }
    }

}