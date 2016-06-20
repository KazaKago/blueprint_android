package com.kazakago.cleanarchitecture.di.module;

import com.kazakago.cleanarchitecture.domain.repository.AboutRepository;
import com.kazakago.cleanarchitecture.domain.repository.CityRepository;
import com.kazakago.cleanarchitecture.domain.repository.WeatherRepository;
import com.kazakago.cleanarchitecture.domain.usecase.AboutUseCase;
import com.kazakago.cleanarchitecture.domain.usecase.AboutUseCaseImpl;
import com.kazakago.cleanarchitecture.domain.usecase.CityUseCase;
import com.kazakago.cleanarchitecture.domain.usecase.CityUseCaseImpl;
import com.kazakago.cleanarchitecture.domain.usecase.WeatherUseCase;
import com.kazakago.cleanarchitecture.domain.usecase.WeatherUseCaseImpl;

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