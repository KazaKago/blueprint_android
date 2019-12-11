package com.kazakago.cleanarchitecture.usecase.di

import com.kazakago.cleanarchitecture.usecase.usecase.about.GetAppInfoUseCase
import com.kazakago.cleanarchitecture.usecase.usecase.about.GetAppInfoUseCaseImpl
import com.kazakago.cleanarchitecture.usecase.usecase.about.GetDeveloperInfoUseCase
import com.kazakago.cleanarchitecture.usecase.usecase.about.GetDeveloperInfoUseCaseImpl
import com.kazakago.cleanarchitecture.usecase.usecase.city.GetCityUseCase
import com.kazakago.cleanarchitecture.usecase.usecase.city.GetCityUseCaseImpl
import com.kazakago.cleanarchitecture.usecase.usecase.weather.GetWeatherUseCase
import com.kazakago.cleanarchitecture.usecase.usecase.weather.GetWeatherUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    single<GetWeatherUseCase> { GetWeatherUseCaseImpl(get()) }
    single<GetCityUseCase> { GetCityUseCaseImpl(get()) }
    single<GetAppInfoUseCase> { GetAppInfoUseCaseImpl(get()) }
    single<GetDeveloperInfoUseCase> { GetDeveloperInfoUseCaseImpl(get()) }
}