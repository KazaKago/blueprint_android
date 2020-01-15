package com.kazakago.cleanarchitecture.data.repository.di

import com.kazakago.cleanarchitecture.domain.repository.about.AboutRepository
import com.kazakago.cleanarchitecture.domain.repository.city.CityRepository
import com.kazakago.cleanarchitecture.data.repository.repository.about.AboutRepositoryImpl
import com.kazakago.cleanarchitecture.data.repository.repository.city.CityRepositoryImpl
import com.kazakago.cleanarchitecture.data.repository.repository.weather.WeatherRepositoryImpl
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherRepository
import org.koin.dsl.module

val dataModule = module {
    single<CityRepository> { CityRepositoryImpl(get()) }
    single<AboutRepository> { AboutRepositoryImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
}