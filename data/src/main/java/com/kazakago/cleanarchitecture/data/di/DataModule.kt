package com.kazakago.cleanarchitecture.data.di

import com.kazakago.cleanarchitecture.data.repository.about.AboutRepositoryImpl
import com.kazakago.cleanarchitecture.data.repository.city.CityRepositoryImpl
import com.kazakago.cleanarchitecture.data.repository.weather.WeatherRepositoryImpl
import com.kazakago.cleanarchitecture.domain.repository.about.AboutRepository
import com.kazakago.cleanarchitecture.domain.repository.city.CityRepository
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherRepository
import org.koin.dsl.module.module

val dataModule = module {
    bean<CityRepository> { CityRepositoryImpl(get()) }
    bean<WeatherRepository> { WeatherRepositoryImpl(get()) }
    bean<AboutRepository> { AboutRepositoryImpl(get()) }
}