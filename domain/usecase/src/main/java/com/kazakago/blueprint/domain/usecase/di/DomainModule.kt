package com.kazakago.blueprint.domain.usecase.di

import com.kazakago.blueprint.domain.usecase.hierarchy.about.SubscribeAboutUseCase
import com.kazakago.blueprint.domain.usecase.hierarchy.about.SubscribeAboutUseCaseImpl
import com.kazakago.blueprint.domain.usecase.hierarchy.city.SubscribeCityListUseCase
import com.kazakago.blueprint.domain.usecase.hierarchy.city.SubscribeCityListUseCaseImpl
import com.kazakago.blueprint.domain.usecase.hierarchy.weather.RequestWeatherUseCase
import com.kazakago.blueprint.domain.usecase.hierarchy.weather.RequestWeatherUseCaseImpl
import com.kazakago.blueprint.domain.usecase.hierarchy.weather.SubscribeWeatherUseCase
import com.kazakago.blueprint.domain.usecase.hierarchy.weather.SubscribeWeatherUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    single<SubscribeWeatherUseCase> { SubscribeWeatherUseCaseImpl(get(), get()) }
    single<RequestWeatherUseCase> { RequestWeatherUseCaseImpl(get()) }
    single<SubscribeCityListUseCase> { SubscribeCityListUseCaseImpl(get()) }
    single<SubscribeAboutUseCase> { SubscribeAboutUseCaseImpl(get()) }
}
