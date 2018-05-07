package com.kazakago.cleanarchitecture.web.di

import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherApiRepository
import com.kazakago.cleanarchitecture.web.repository.weather.WeatherApiRepositoryImpl
import org.koin.dsl.module.applicationContext

val webModule = applicationContext {
    bean<WeatherApiRepository> { WeatherApiRepositoryImpl(get()) }
}