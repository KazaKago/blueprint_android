package com.kazakago.cleanarchitecture.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.kazakago.cleanarchitecture.domain.usecase.appInfo.*
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCaseImpl
import com.kazakago.cleanarchitecture.domain.usecase.weather.GetWeatherUseCase
import com.kazakago.cleanarchitecture.domain.usecase.weather.GetWeatherUseCaseImpl

fun domainModule() = Kodein.Module {
    bind<GetWeatherUseCase>() with provider { GetWeatherUseCaseImpl(weatherApiRepository = instance(), weatherRepository = instance()) }
    bind<GetCityUseCase>() with provider { GetCityUseCaseImpl(cityRepository = instance()) }
    bind<GetAppVersionUseCase>() with provider { GetAppVersionUseCaseImpl(appInfoRepository = instance()) }
    bind<GetMailAddressUrlUseCase>() with provider { GetMailAddressUrlUseCaseImpl(appInfoRepository = instance()) }
    bind<GetOfficialSiteUrlUseCase>() with provider { GetOfficialSiteUrlUseCaseImpl(appInfoRepository = instance()) }
    bind<GetPlayStoreUrlUseCase>() with provider { GetPlayStoreUrlUseCaseImpl(appInfoRepository = instance()) }
}