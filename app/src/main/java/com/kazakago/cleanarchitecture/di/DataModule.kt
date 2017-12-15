package com.kazakago.cleanarchitecture.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.kazakago.cleanarchitecture.data.repository.appinfo.AppInfoRepositoryImpl
import com.kazakago.cleanarchitecture.data.repository.city.CityRepositoryImpl
import com.kazakago.cleanarchitecture.data.repository.weather.WeatherRepositoryImpl
import com.kazakago.cleanarchitecture.domain.repository.appinfo.AppInfoRepository
import com.kazakago.cleanarchitecture.domain.repository.city.CityRepository
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherRepository

fun dataModule() = Kodein.Module {
    bind<CityRepository>() with provider { CityRepositoryImpl(instance()) }
    bind<WeatherRepository>() with provider { WeatherRepositoryImpl(instance()) }
    bind<AppInfoRepository>() with provider { AppInfoRepositoryImpl(instance()) }
}