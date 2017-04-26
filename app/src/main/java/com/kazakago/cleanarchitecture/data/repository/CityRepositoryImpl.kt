package com.kazakago.cleanarchitecture.data.repository

import android.content.Context
import com.kazakago.cleanarchitecture.data.dao.CityDao
import com.kazakago.cleanarchitecture.data.mapper.CityMapper
import com.kazakago.cleanarchitecture.domain.model.city.CityModel
import com.kazakago.cleanarchitecture.domain.repository.CityRepository
import io.reactivex.Observable
import java.io.IOException

/**
 * City Repository Implement
 *
 *
 * Created by tamura_k on 2016/05/27.
 */
class CityRepositoryImpl(private val context: Context) : CityRepository {

    @Throws(IOException::class)
    override fun findAll(): Observable<CityModel> {
        val cityDao = CityDao(context)
        val cityList = cityDao.find().flatMap {
            CityMapper.execute(it)
        }
        return Observable.fromIterable(cityList)
    }

}
