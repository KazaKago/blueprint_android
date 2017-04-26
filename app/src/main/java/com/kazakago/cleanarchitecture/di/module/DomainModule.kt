package com.kazakago.cleanarchitecture.di.module

import com.kazakago.cleanarchitecture.domain.repository.AboutRepository
import com.kazakago.cleanarchitecture.domain.repository.CityRepository
import com.kazakago.cleanarchitecture.domain.repository.WeatherRepository
import com.kazakago.cleanarchitecture.domain.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideCityUseCase(cityRepository: CityRepository): CityUseCase {
        return CityUseCaseImpl(cityRepository)
    }

    @Provides
    @Singleton
    fun provideWeatherUseCase(weatherRepository: WeatherRepository): WeatherUseCase {
        return WeatherUseCaseImpl(weatherRepository)
    }

    @Provides
    @Singleton
    fun provideAboutUseCase(aboutRepository: AboutRepository): AboutUseCase {
        return AboutUseCaseImpl(aboutRepository)
    }

}