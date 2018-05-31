package com.kazakago.cleanarchitecture.domain.di

import com.kazakago.cleanarchitecture.domain.usecase.about.GetAppInfoUseCase
import com.kazakago.cleanarchitecture.domain.usecase.about.GetAppInfoUseCaseImpl
import com.kazakago.cleanarchitecture.domain.usecase.about.GetDeveloperInfoUseCase
import com.kazakago.cleanarchitecture.domain.usecase.about.GetDeveloperInfoUseCaseImpl
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCaseImpl
import com.kazakago.cleanarchitecture.domain.usecase.weather.GetWeatherUseCase
import com.kazakago.cleanarchitecture.domain.usecase.weather.GetWeatherUseCaseImpl
import org.koin.core.KoinContext
import org.koin.dsl.context.ModuleDefinition
import org.koin.dsl.module.module
import org.koin.dsl.path.Path
import org.koin.standalone.StandAloneContext

val domainModule = module {
    ModuleDefinition(Path.ROOT, StandAloneContext.koinContext as KoinContext).apply {
        bean<GetWeatherUseCase> { GetWeatherUseCaseImpl(get(), get()) }
        bean<GetCityUseCase> { GetCityUseCaseImpl(get()) }
        bean<GetAppInfoUseCase> { GetAppInfoUseCaseImpl(get()) }
        bean<GetDeveloperInfoUseCase> { GetDeveloperInfoUseCaseImpl(get()) }
    }
}