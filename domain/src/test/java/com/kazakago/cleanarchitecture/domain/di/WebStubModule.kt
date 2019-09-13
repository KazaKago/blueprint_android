package com.kazakago.cleanarchitecture.domain.di

import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherApiRepository
import com.kazakago.cleanarchitecture.domain.stub.web.repository.weather.WeatherApiRepositoryStub
import org.koin.dsl.module

val webStubModule = module {
    single<WeatherApiRepository> { WeatherApiRepositoryStub() }
}
