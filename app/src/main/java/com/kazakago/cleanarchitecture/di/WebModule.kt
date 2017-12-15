package com.kazakago.cleanarchitecture.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.provider
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherApiRepository
import com.kazakago.cleanarchitecture.web.repository.weather.WeatherApiRepositoryImpl

fun webModule() = Kodein.Module {
    bind<WeatherApiRepository>() with provider { WeatherApiRepositoryImpl() }
}