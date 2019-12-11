package com.kazakago.cleanarchitecture.repository.di

import com.kazakago.cleanarchitecture.repository.about.AboutRepository
import com.kazakago.cleanarchitecture.repository.city.CityRepository
import com.kazakago.cleanarchitecture.repository.repository.about.AboutRepositoryImpl
import com.kazakago.cleanarchitecture.repository.repository.city.CityRepositoryImpl
import com.kazakago.cleanarchitecture.repository.repository.weather.WeatherRepositoryImpl
import com.kazakago.cleanarchitecture.repository.weather.WeatherRepository
import org.koin.dsl.module

val dataModule = module {
    single<CityRepository> { CityRepositoryImpl(get()) }
    single<AboutRepository> { AboutRepositoryImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
}