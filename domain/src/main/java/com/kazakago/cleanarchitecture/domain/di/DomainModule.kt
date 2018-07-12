package com.kazakago.cleanarchitecture.domain.di

import com.kazakago.cleanarchitecture.domain.usecase.about.GetAppInfoUseCase
import com.kazakago.cleanarchitecture.domain.usecase.about.GetAppInfoUseCaseImpl
import com.kazakago.cleanarchitecture.domain.usecase.about.GetDeveloperInfoUseCase
import com.kazakago.cleanarchitecture.domain.usecase.about.GetDeveloperInfoUseCaseImpl
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCaseImpl
import com.kazakago.cleanarchitecture.domain.usecase.weather.GetWeatherUseCase
import com.kazakago.cleanarchitecture.domain.usecase.weather.GetWeatherUseCaseImpl
import org.koin.dsl.module.module

val domainModule = module {
    single<GetWeatherUseCase> { GetWeatherUseCaseImpl(get(), get()) }
    single<GetCityUseCase> { GetCityUseCaseImpl(get()) }
    single<GetAppInfoUseCase> { GetAppInfoUseCaseImpl(get()) }
    single<GetDeveloperInfoUseCase> { GetDeveloperInfoUseCaseImpl(get()) }
}