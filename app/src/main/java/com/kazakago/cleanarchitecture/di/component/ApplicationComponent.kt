package com.kazakago.cleanarchitecture.di.component

import com.kazakago.cleanarchitecture.di.module.ApplicationModule
import com.kazakago.cleanarchitecture.di.module.DataModule
import com.kazakago.cleanarchitecture.di.module.DomainModule
import com.kazakago.cleanarchitecture.di.module.WebModule
import com.kazakago.cleanarchitecture.presentation.presenter.fragment.AboutFragmentViewModel
import com.kazakago.cleanarchitecture.presentation.presenter.fragment.MainFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, DomainModule::class, DataModule::class, WebModule::class))
interface ApplicationComponent {

    fun inject(mainFragmentViewModel: MainFragmentViewModel)

    fun inject(aboutFragmentViewModel: AboutFragmentViewModel)

}
