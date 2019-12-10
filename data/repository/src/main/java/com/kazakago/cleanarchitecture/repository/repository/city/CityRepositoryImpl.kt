package com.kazakago.cleanarchitecture.repository.repository.city

import android.content.Context
import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.repository.city.CityRepository
import com.kazakago.cleanarchitecture.repository.mapper.city.CityMapper
import com.kazakago.cleanarchitecture.resource.dao.city.CityDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class CityRepositoryImpl(private val context: Context) : CityRepository {

    override suspend fun findAll(): List<City> = withContext(Dispatchers.IO) {
        val cityDao = CityDao(context)
        val prefEntityList = cityDao.find()
        prefEntityList.flatMap { CityMapper.map(it) }
    }

}
