package com.kazakago.cleanarchitecture.web.di

import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherApiRepository
import com.kazakago.cleanarchitecture.web.repository.weather.WeatherApiRepositoryImpl
import org.koin.core.KoinContext
import org.koin.dsl.context.ModuleDefinition
import org.koin.dsl.module.module
import org.koin.dsl.path.Path
import org.koin.standalone.StandAloneContext

val webModule = module {
    ModuleDefinition(Path.ROOT, StandAloneContext.koinContext as KoinContext).apply {
        bean<WeatherApiRepository> { WeatherApiRepositoryImpl(get()) }
    }
}