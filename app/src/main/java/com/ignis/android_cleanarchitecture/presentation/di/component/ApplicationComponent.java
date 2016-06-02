package com.ignis.android_cleanarchitecture.presentation.di.component;

import com.ignis.android_cleanarchitecture.presentation.di.module.ApplicationModule;
import com.ignis.android_cleanarchitecture.presentation.di.module.DataModule;
import com.ignis.android_cleanarchitecture.presentation.di.module.DomainModule;
import com.ignis.android_cleanarchitecture.presentation.presenter.fragment.AboutFragmentViewModel;
import com.ignis.android_cleanarchitecture.presentation.presenter.fragment.MainFragmentViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, DomainModule.class, DataModule.class})
public interface ApplicationComponent {

    void inject(MainFragmentViewModel mainFragmentViewModel);

    void inject(AboutFragmentViewModel aboutFragmentViewModel);

}
