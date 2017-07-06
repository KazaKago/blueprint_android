package com.kazakago.cleanarchitecture.di.module

import android.content.Context
import com.kazakago.cleanarchitecture.data.repository.AppInfoRepositoryImpl
import com.kazakago.cleanarchitecture.data.repository.CityRepositoryImpl
import com.kazakago.cleanarchitecture.data.repository.WeatherRepositoryImpl
import com.kazakago.cleanarchitecture.domain.repository.AppInfoRepository
import com.kazakago.cleanarchitecture.domain.repository.CityRepository
import com.kazakago.cleanarchitecture.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideCityRepository(context: Context): CityRepository {
        return CityRepositoryImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(): WeatherRepository {
        return WeatherRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideAboutRepository(context: Context): AppInfoRepository {
        return AppInfoRepositoryImpl(context = context)
    }

}