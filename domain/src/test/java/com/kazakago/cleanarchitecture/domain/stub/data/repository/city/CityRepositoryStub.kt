package com.kazakago.cleanarchitecture.domain.stub.data.repository.city

import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.repository.city.CityRepository

class CityRepositoryStub : CityRepository {

    override suspend fun findAll(): List<City> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}