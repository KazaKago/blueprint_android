package com.weathercock.cleanarchitecture.di.module;

import android.content.Context;

import com.weathercock.cleanarchitecture.data.repository.AboutRepositoryImpl;
import com.weathercock.cleanarchitecture.data.repository.CityRepositoryImpl;
import com.weathercock.cleanarchitecture.data.repository.WeatherRepositoryImpl;
import com.weathercock.cleanarchitecture.domain.repository.AboutRepository;
import com.weathercock.cleanarchitecture.domain.repository.CityRepository;
import com.weathercock.cleanarchitecture.domain.repository.WeatherRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    public CityRepository provideCityRepository() {
        return new CityRepositoryImpl();
    }

    @Provides
    @Singleton
    public WeatherRepository provideWeatherRepository() {
        return new WeatherRepositoryImpl();
    }

    @Provides
    @Singleton
    public AboutRepository provideAboutRepository(Context context) {
        return new AboutRepositoryImpl(context);
    }

}