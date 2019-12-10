package com.kazakago.cleanarchitecture.repository.city

import com.kazakago.cleanarchitecture.model.city.City

interface CityRepository {

    suspend fun findAll(): List<City>

}
