package com.kazakago.cleanarchitecture.domain.repository.city

import com.kazakago.cleanarchitecture.domain.model.city.City

interface CityRepository {

    suspend fun findAll(): List<City>

}
