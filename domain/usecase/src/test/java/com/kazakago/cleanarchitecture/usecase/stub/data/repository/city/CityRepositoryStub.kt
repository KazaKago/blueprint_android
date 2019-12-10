package com.kazakago.cleanarchitecture.usecase.stub.data.repository.city

import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.repository.city.CityRepository

class CityRepositoryStub : CityRepository {

    override suspend fun findAll(): List<City> {
        return listOf(
            City(CityId("1"), "City1"),
            City(CityId("2"), "City2"),
            City(CityId("3"), "City3"),
            City(CityId("4"), "City4"),
            City(CityId("5"), "City5"),
            City(CityId("6"), "City6"),
            City(CityId("7"), "City7"),
            City(CityId("8"), "City8"),
            City(CityId("9"), "City9"),
            City(CityId("10"), "City10"),
            City(CityId("11"), "City11"),
            City(CityId("12"), "City12"),
            City(CityId("13"), "City13"),
            City(CityId("14"), "City14")
        )
    }

}