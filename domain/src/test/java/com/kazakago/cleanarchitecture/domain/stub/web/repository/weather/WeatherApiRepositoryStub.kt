package com.kazakago.cleanarchitecture.domain.stub.web.repository.weather

import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherApiRepository

class WeatherApiRepositoryStub : WeatherApiRepository {

    override suspend fun fetch(cityId: CityId): Weather {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}