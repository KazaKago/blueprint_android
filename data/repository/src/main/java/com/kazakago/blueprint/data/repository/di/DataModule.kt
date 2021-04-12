package com.kazakago.blueprint.data.repository.di

import com.kazakago.blueprint.data.repository.hierarchy.about.AboutRepositoryImpl
import com.kazakago.blueprint.data.repository.hierarchy.city.CityRepositoryImpl
import com.kazakago.blueprint.data.repository.hierarchy.weather.WeatherRepositoryImpl
import com.kazakago.blueprint.domain.repository.hierarchy.about.AboutRepository
import com.kazakago.blueprint.domain.repository.hierarchy.city.CityRepository
import com.kazakago.blueprint.domain.repository.hierarchy.weather.WeatherRepository
import org.koin.dsl.module

val dataModule = module {
    single<CityRepository> { CityRepositoryImpl(get()) }
    single<AboutRepository> { AboutRepositoryImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
}
