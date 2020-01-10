package com.kazakago.cleanarchitecture.usecase.di

import com.kazakago.cleanarchitecture.usecase.usecase.about.SubscribeAppInfoUseCase
import com.kazakago.cleanarchitecture.usecase.usecase.about.SubscribeAppInfoUseCaseImpl
import com.kazakago.cleanarchitecture.usecase.usecase.about.SubscribeDeveloperInfoUseCase
import com.kazakago.cleanarchitecture.usecase.usecase.about.SubscribeDeveloperInfoUseCaseImpl
import com.kazakago.cleanarchitecture.usecase.usecase.city.SubscribeCityListUseCase
import com.kazakago.cleanarchitecture.usecase.usecase.city.SubscribeCityListUseCaseImpl
import com.kazakago.cleanarchitecture.usecase.usecase.city.SubscribeCityUseCase
import com.kazakago.cleanarchitecture.usecase.usecase.city.SubscribeCityUseCaseImpl
import com.kazakago.cleanarchitecture.usecase.usecase.weather.SubscribeWeatherUseCase
import com.kazakago.cleanarchitecture.usecase.usecase.weather.SubscribeWeatherUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    single<SubscribeWeatherUseCase> { SubscribeWeatherUseCaseImpl(get()) }
    single<SubscribeCityListUseCase> { SubscribeCityListUseCaseImpl(get()) }
    single<SubscribeCityUseCase> { SubscribeCityUseCaseImpl(get()) }
    single<SubscribeAppInfoUseCase> { SubscribeAppInfoUseCaseImpl(get()) }
    single<SubscribeDeveloperInfoUseCase> { SubscribeDeveloperInfoUseCaseImpl(get()) }
}