package com.kazakago.cleanarchitecture.di.module

import com.kazakago.cleanarchitecture.domain.repository.WeatherApiRepository
import com.kazakago.cleanarchitecture.web.repository.WeatherApiRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WebModule {

    @Provides
    @Singleton
    fun provideWeatherApiRepository(): WeatherApiRepository {
        return WeatherApiRepositoryImpl()
    }

}