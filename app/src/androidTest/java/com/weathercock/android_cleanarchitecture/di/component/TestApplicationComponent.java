package com.weathercock.android_cleanarchitecture.di.component;

import com.weathercock.android_cleanarchitecture.domain.usecase.AboutUseCaseTest;
import com.weathercock.android_cleanarchitecture.domain.usecase.CityUseCaseTest;
import com.weathercock.android_cleanarchitecture.domain.usecase.WeatherUseCaseTest;
import com.weathercock.android_cleanarchitecture.di.module.ApplicationModule;
import com.weathercock.android_cleanarchitecture.di.module.DataModule;
import com.weathercock.android_cleanarchitecture.di.module.DomainModule;

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