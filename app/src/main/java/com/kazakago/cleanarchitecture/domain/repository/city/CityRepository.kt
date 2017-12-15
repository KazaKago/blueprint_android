package com.kazakago.cleanarchitecture.domain.repository.city

import com.kazakago.cleanarchitecture.domain.model.city.City
import io.reactivex.Observable
import java.io.IOException

interface CityRepository {

    @Throws(IOException::class)
    fun findAll(): Observable<City>

}
