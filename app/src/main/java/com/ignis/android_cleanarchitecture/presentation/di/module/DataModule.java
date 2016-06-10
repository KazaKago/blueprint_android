package com.ignis.android_cleanarchitecture.presentation.di.module;

import android.content.Context;

import com.ignis.android_cleanarchitecture.data.repository.AboutRepositoryImpl;
import com.ignis.android_cleanarchitecture.data.repository.CityRepositoryImpl;
import com.ignis.android_cleanarchitecture.data.repository.WeatherRepositoryImpl;
import com.ignis.android_cleanarchitecture.domain.repository.AboutRepository;
import com.ignis.android_cleanarchitecture.domain.repository.CityRepository;
import com.ignis.android_cleanarchitecture.domain.repository.WeatherRepository;

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
    public WeatherRepository provideWeatherRepository(Context context) {
        return new WeatherRepositoryImpl(context);
    }

    @Provides
    @Singleton
    public AboutRepository provideAboutRepository(Context context) {
        return new AboutRepositoryImpl(context);
    }

}