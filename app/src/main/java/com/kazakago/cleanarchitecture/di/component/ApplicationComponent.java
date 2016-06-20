package com.kazakago.cleanarchitecture.di.component;

import com.kazakago.cleanarchitecture.di.module.ApplicationModule;
import com.kazakago.cleanarchitecture.di.module.DataModule;
import com.kazakago.cleanarchitecture.di.module.DomainModule;
import com.kazakago.cleanarchitecture.presentation.presenter.fragment.AboutFragmentViewModel;
import com.kazakago.cleanarchitecture.presentation.presenter.fragment.MainFragmentViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, DomainModule.class, DataModule.class})
public interface ApplicationComponent {

    void inject(MainFragmentViewModel mainFragmentViewModel);

    void inject(AboutFragmentViewModel aboutFragmentViewModel);

}
