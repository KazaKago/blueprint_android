package com.kazakago.cleanarchitecture.data.repository.city

import android.content.Context
import com.kazakago.cleanarchitecture.data.file.dao.city.CityDao
import com.kazakago.cleanarchitecture.data.file.mapper.city.CityMapper
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.repository.city.CityRepository

class CityRepositoryImpl(private val context: Context) : CityRepository {

    override fun findAll(): List<City> {
        val cityDao = CityDao(context)
        val prefEntityList = cityDao.find()
        return prefEntityList.flatMap { CityMapper.map(it) }
    }

}
