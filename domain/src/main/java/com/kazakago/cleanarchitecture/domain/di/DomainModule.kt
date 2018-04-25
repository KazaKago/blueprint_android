package com.kazakago.cleanarchitecture.domain.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.kazakago.cleanarchitecture.domain.usecase.about.GetAppInfoUseCase
import com.kazakago.cleanarchitecture.domain.usecase.about.GetAppInfoUseCaseImpl
import com.kazakago.cleanarchitecture.domain.usecase.about.GetDeveloperInfoUseCase
import com.kazakago.cleanarchitecture.domain.usecase.about.GetDeveloperInfoUseCaseImpl
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCaseImpl
import com.kazakago.cleanarchitecture.domain.usecase.weather.GetWeatherUseCase
import com.kazakago.cleanarchitecture.domain.usecase.weather.GetWeatherUseCaseImpl

fun domainModule() = Kodein.Module {
    bind<GetWeatherUseCase>() with provider { GetWeatherUseCaseImpl(instance(), instance()) }
    bind<GetCityUseCase>() with provider { GetCityUseCaseImpl(instance()) }
    bind<GetAppInfoUseCase>() with provider { GetAppInfoUseCaseImpl(instance()) }
    bind<GetDeveloperInfoUseCase>() with provider { GetDeveloperInfoUseCaseImpl(instance()) }
}
