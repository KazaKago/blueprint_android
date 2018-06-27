package com.kazakago.cleanarchitecture.web.di

import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherApiRepository
import com.kazakago.cleanarchitecture.web.repository.weather.WeatherApiRepositoryImpl
import org.koin.dsl.module.module

val webModule = module {
    bean<WeatherApiRepository> { WeatherApiRepositoryImpl(get()) }
}