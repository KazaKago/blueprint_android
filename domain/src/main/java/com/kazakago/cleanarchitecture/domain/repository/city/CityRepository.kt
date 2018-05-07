package com.kazakago.cleanarchitecture.domain.repository.city

import com.kazakago.cleanarchitecture.domain.model.city.City

interface CityRepository {

    fun findAll(): List<City>

}
