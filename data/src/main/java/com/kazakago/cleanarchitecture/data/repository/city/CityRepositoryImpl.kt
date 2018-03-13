package com.kazakago.cleanarchitecture.data.repository.city

import android.content.Context
import com.kazakago.cleanarchitecture.data.file.dao.city.CityDao
import com.kazakago.cleanarchitecture.data.file.mapper.city.CityMapper
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.repository.city.CityRepository
import io.reactivex.Single

class CityRepositoryImpl(private val context: Context) : CityRepository {

    override fun findAll(): Single<List<City>> {
        val cityDao = CityDao(context)
        val cityList = cityDao.find().flatMap { CityMapper.map(it) }
        return Single.just(cityList)
    }

}
