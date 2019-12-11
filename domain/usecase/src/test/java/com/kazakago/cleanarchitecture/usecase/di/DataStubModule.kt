package com.kazakago.cleanarchitecture.usecase.di

import com.kazakago.cleanarchitecture.repository.about.AboutRepository
import com.kazakago.cleanarchitecture.repository.city.CityRepository
import com.kazakago.cleanarchitecture.repository.weather.WeatherRepository
import com.kazakago.cleanarchitecture.usecase.stub.data.repository.about.AboutRepositoryStub
import com.kazakago.cleanarchitecture.usecase.stub.data.repository.city.CityRepositoryStub
import com.kazakago.cleanarchitecture.usecase.stub.web.repository.weather.WeatherRepositoryStub
import org.koin.dsl.module

val dataStubModule = module {
    single<CityRepository> { CityRepositoryStub() }
    single<AboutRepository> { AboutRepositoryStub() }
    single<WeatherRepository> { WeatherRepositoryStub() }
}