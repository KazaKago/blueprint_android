package com.kazakago.cleanarchitecture.data.repository.distributor.city

import android.content.Context
import com.kazakago.cleanarchitecture.data.resource.hierarchy.city.CityDao
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.City
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId
import com.kazakago.cleanarchitecture.data.repository.mapper.city.CityEntityMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class CityDistributor(context: Context) {

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