package com.kazakago.cleanarchitecture.domain.stub.data.repository.weather

import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherRepository

class WeatherRepositoryStub : WeatherRepository {

    override suspend fun find(cityId: CityId): Weather {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun insert(weather: Weather) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun delete(weather: Weather) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}