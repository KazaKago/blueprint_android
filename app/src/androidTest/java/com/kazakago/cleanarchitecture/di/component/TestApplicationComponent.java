package com.kazakago.cleanarchitecture.di.component;

import com.kazakago.cleanarchitecture.domain.usecase.AboutUseCaseTest;
import com.kazakago.cleanarchitecture.domain.usecase.CityUseCaseTest;
import com.kazakago.cleanarchitecture.domain.usecase.WeatherUseCaseTest;
import com.kazakago.cleanarchitecture.di.module.ApplicationModule;
import com.kazakago.cleanarchitecture.di.module.DataModule;
import com.kazakago.cleanarchitecture.di.module.DomainModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tamura_k on 2016/06/15.
 */
@Singleton
@Component(modules = {ApplicationModule.class, DomainModule.class, DataModule.class})
public interface TestApplicationComponent {

    void inject(AboutUseCaseTest aboutUseCaseTest);

    void inject(CityUseCaseTest cityUseCaseTest);

    void inject(WeatherUseCaseTest weatherUseCaseTest);

}