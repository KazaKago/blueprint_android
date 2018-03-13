package com.kazakago.cleanarchitecture.domain.repository.city

import com.kazakago.cleanarchitecture.domain.model.city.City
import io.reactivex.Single

interface CityRepository {

    fun findAll(): Single<List<City>>

}
