package com.weathercock.cleanarchitecture.di.component;

import com.weathercock.cleanarchitecture.di.module.ApplicationModule;
import com.weathercock.cleanarchitecture.di.module.DataModule;
import com.weathercock.cleanarchitecture.di.module.DomainModule;
import com.weathercock.cleanarchitecture.presentation.presenter.fragment.AboutFragmentViewModel;
import com.weathercock.cleanarchitecture.presentation.presenter.fragment.MainFragmentViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, DomainModule.class, DataModule.class})
public interface ApplicationComponent {

    void inject(MainFragmentViewModel mainFragmentViewModel);

    void inject(AboutFragmentViewModel aboutFragmentViewModel);

}
