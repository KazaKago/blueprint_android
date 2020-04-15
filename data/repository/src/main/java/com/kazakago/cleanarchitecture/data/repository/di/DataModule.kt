package com.kazakago.cleanarchitecture.data.repository.di

import com.kazakago.cleanarchitecture.data.repository.hierarchy.about.AboutRepositoryImpl
import com.kazakago.cleanarchitecture.data.repository.hierarchy.city.CityRepositoryImpl
import com.kazakago.cleanarchitecture.data.repository.hierarchy.weather.WeatherRepositoryImpl
import com.kazakago.cleanarchitecture.domain.repository.hierarchy.about.AboutRepository
import com.kazakago.cleanarchitecture.domain.repository.hierarchy.city.CityRepository
import com.kazakago.cleanarchitecture.domain.repository.hierarchy.weather.WeatherRepository
import org.koin.dsl.module

val dataModule = module {
    single<CityRepository> { CityRepositoryImpl(get()) }
    single<AboutRepository> { AboutRepositoryImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
}