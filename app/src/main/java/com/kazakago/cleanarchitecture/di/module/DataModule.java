package com.kazakago.cleanarchitecture.di.module;

import android.content.Context;

import com.kazakago.cleanarchitecture.data.repository.AboutRepositoryImpl;
import com.kazakago.cleanarchitecture.data.repository.CityRepositoryImpl;
import com.kazakago.cleanarchitecture.data.repository.WeatherRepositoryImpl;
import com.kazakago.cleanarchitecture.domain.repository.AboutRepository;
import com.kazakago.cleanarchitecture.domain.repository.CityRepository;
import com.kazakago.cleanarchitecture.domain.repository.WeatherRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    public CityRepository provideCityRepository(Context context) {
        return new CityRepositoryImpl(context);
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