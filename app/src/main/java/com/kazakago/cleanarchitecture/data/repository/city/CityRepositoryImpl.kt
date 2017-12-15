package com.kazakago.cleanarchitecture.data.repository.city

import android.content.Context
import com.kazakago.cleanarchitecture.data.file.dao.city.CityDao
import com.kazakago.cleanarchitecture.data.file.mapper.city.CityMapper
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.repository.city.CityRepository
import io.reactivex.Observable
import java.io.IOException

class CityRepositoryImpl(private val context: Context) : CityRepository {

    @Throws(IOException::class)
    override fun findAll(): Observable<City> {
        val cityDao = CityDao(context)
        val cityList = cityDao.find().flatMap { CityMapper.map(it) }
        return Observable.fromIterable(cityList)
    }

}
