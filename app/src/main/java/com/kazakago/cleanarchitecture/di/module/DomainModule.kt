package com.kazakago.cleanarchitecture.di.module

import com.kazakago.cleanarchitecture.domain.repository.AppInfoRepository
import com.kazakago.cleanarchitecture.domain.repository.CityRepository
import com.kazakago.cleanarchitecture.domain.repository.WeatherApiRepository
import com.kazakago.cleanarchitecture.domain.repository.WeatherRepository
import com.kazakago.cleanarchitecture.domain.usecase.appInfo.*
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCaseImpl
import com.kazakago.cleanarchitecture.domain.usecase.weather.GetWeatherUseCase
import com.kazakago.cleanarchitecture.domain.usecase.weather.GetWeatherUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideGetWeatherUseCase(weatherApiRepository: WeatherApiRepository, weatherRepository: WeatherRepository): GetWeatherUseCase {
        return GetWeatherUseCaseImpl(weatherApiRepository = weatherApiRepository, weatherRepository = weatherRepository)
    }

    @Provides
    @Singleton
    fun provideGetCityUseCase(cityRepository: CityRepository): GetCityUseCase {
        return GetCityUseCaseImpl(cityRepository = cityRepository)
    }

    @Provides
    @Singleton
    fun provideGetAppVersionUseCase(appInfoRepository: AppInfoRepository): GetAppVersionUseCase {
        return GetAppVersionUseCaseImpl(appInfoRepository = appInfoRepository)
    }

    @Provides
    @Singleton
    fun provideGetMailAddressUrlUseCase(appInfoRepository: AppInfoRepository): GetMailAddressUrlUseCase {
        return GetMailAddressUrlUseCaseImpl(appInfoRepository = appInfoRepository)
    }

    @Provides
    @Singleton
    fun provideGetOfficialSiteUrlUseCase(appInfoRepository: AppInfoRepository): GetOfficialSiteUrlUseCase {
        return GetOfficialSiteUrlUseCaseImpl(appInfoRepository = appInfoRepository)
    }

    @Provides
    @Singleton
    fun provideGetPlayStoreUrlUseCase(appInfoRepository: AppInfoRepository): GetPlayStoreUrlUseCase {
        return GetPlayStoreUrlUseCaseImpl(appInfoRepository = appInfoRepository)
    }

}