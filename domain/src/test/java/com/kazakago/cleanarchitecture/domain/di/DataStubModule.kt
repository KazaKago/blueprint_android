package com.kazakago.cleanarchitecture.domain.di

import com.kazakago.cleanarchitecture.domain.repository.about.AboutRepository
import com.kazakago.cleanarchitecture.domain.repository.city.CityRepository
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherRepository
import com.kazakago.cleanarchitecture.domain.stub.data.repository.about.AboutRepositoryStub
import com.kazakago.cleanarchitecture.domain.stub.data.repository.city.CityRepositoryStub
import com.kazakago.cleanarchitecture.domain.stub.data.repository.weather.WeatherRepositoryStub
import org.koin.dsl.module

val dataStubModule = module {
    single<CityRepository> { CityRepositoryStub() }
    single<WeatherRepository> { WeatherRepositoryStub() }
    single<AboutRepository> { AboutRepositoryStub() }
}