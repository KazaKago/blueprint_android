package com.kazakago.cleanarchitecture.repository.repository.city

import android.content.Context
import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.repository.city.CityRepository
import com.kazakago.cleanarchitecture.repository.mapper.city.CityMapper
import com.kazakago.cleanarchitecture.resource.dao.city.CityDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class CityRepositoryImpl(context: Context) : CityRepository {

    private val cityDao = CityDao(context)
    private val cityMapper = CityMapper()

    override suspend fun findAll(): List<City> = withContext(Dispatchers.IO) {
        val prefEntityList = cityDao.find()
        prefEntityList.flatMap { cityMapper.map(it) }
    }

}
