package com.kazakago.cleanarchitecture.domain.repository

import com.kazakago.cleanarchitecture.domain.model.city.CityModel
import io.reactivex.Observable
import java.io.IOException

interface CityRepository {

    @Throws(IOException::class)
    fun findAll(): Observable<CityModel>

}
