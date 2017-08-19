package com.kazakago.cleanarchitecture.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.kazakago.cleanarchitecture.data.repository.AppInfoRepositoryImpl
import com.kazakago.cleanarchitecture.data.repository.CityRepositoryImpl
import com.kazakago.cleanarchitecture.data.repository.WeatherRepositoryImpl
import com.kazakago.cleanarchitecture.domain.repository.AppInfoRepository
import com.kazakago.cleanarchitecture.domain.repository.CityRepository
import com.kazakago.cleanarchitecture.domain.repository.WeatherRepository

fun dataModule() = Kodein.Module {
    bind<CityRepository>() with provider { CityRepositoryImpl(context = instance()) }
    bind<WeatherRepository>() with provider { WeatherRepositoryImpl() }
    bind<AppInfoRepository>() with provider { AppInfoRepositoryImpl(context = instance()) }
}