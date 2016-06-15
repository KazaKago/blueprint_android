package com.weathercock.cleanarchitecture.di.module;

import com.weathercock.cleanarchitecture.domain.repository.AboutRepository;
import com.weathercock.cleanarchitecture.domain.repository.CityRepository;
import com.weathercock.cleanarchitecture.domain.repository.WeatherRepository;
import com.weathercock.cleanarchitecture.domain.usecase.AboutUseCase;
import com.weathercock.cleanarchitecture.domain.usecase.AboutUseCaseImpl;
import com.weathercock.cleanarchitecture.domain.usecase.CityUseCase;
import com.weathercock.cleanarchitecture.domain.usecase.CityUseCaseImpl;
import com.weathercock.cleanarchitecture.domain.usecase.WeatherUseCase;
import com.weathercock.cleanarchitecture.domain.usecase.WeatherUseCaseImpl;

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