package com.kazakago.cleanarchitecture.data.repository.city

import android.content.Context
import com.kazakago.cleanarchitecture.data.file.dao.city.CityDao
import com.kazakago.cleanarchitecture.data.file.mapper.city.CityMapper
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.repository.city.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class CityRepositoryImpl(private val context: Context) : CityRepository {

    override suspend fun findAll(): List<City> {
        return GlobalScope.async(Dispatchers.Default) {
            val cityDao = CityDao(context)
            val prefEntityList = cityDao.find()
            prefEntityList.flatMap { CityMapper.map(it) }
        }.await()
    }

}
