package com.ignis.android_cleanarchitecture.presentation.di.module;

import com.ignis.android_cleanarchitecture.domain.repository.AboutRepository;
import com.ignis.android_cleanarchitecture.domain.repository.CityRepository;
import com.ignis.android_cleanarchitecture.domain.repository.WeatherRepository;
import com.ignis.android_cleanarchitecture.domain.usecase.AboutUseCase;
import com.ignis.android_cleanarchitecture.domain.usecase.AboutUseCaseImpl;
import com.ignis.android_cleanarchitecture.domain.usecase.CityUseCase;
import com.ignis.android_cleanarchitecture.domain.usecase.CityUseCaseImpl;
import com.ignis.android_cleanarchitecture.domain.usecase.WeatherUseCase;
import com.ignis.android_cleanarchitecture.domain.usecase.WeatherUseCaseImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DomainModule {

    @Provides
    @Singleton
    public CityUseCase provideCityUseCase(CityRepository cityRepository) {
        return new CityUseCaseImpl(cityRepository);
    }

    @Provides
    @Singleton
    public WeatherUseCase provideWeatherUseCase(WeatherRepository weatherRepository) {
        return new WeatherUseCaseImpl(weatherRepository);
    }

    @Provides
    @Singleton
    public AboutUseCase provideAboutUseCase(AboutRepository aboutRepository) {
        return new AboutUseCaseImpl(aboutRepository);
    }

}