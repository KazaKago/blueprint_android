package com.kazakago.cleanarchitecture.di.component

import com.kazakago.cleanarchitecture.di.module.ApplicationModule
import com.kazakago.cleanarchitecture.di.module.DataModule
import com.kazakago.cleanarchitecture.di.module.DomainModule
import com.kazakago.cleanarchitecture.di.module.WebModule
import com.kazakago.cleanarchitecture.domain.usecase.AppInfoUseCaseTest
import com.kazakago.cleanarchitecture.domain.usecase.CityUseCaseTest
import com.kazakago.cleanarchitecture.domain.usecase.WeatherUseCaseTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, DomainModule::class, DataModule::class, WebModule::class))
interface TestApplicationComponent {

    fun inject(appInfoUseCaseTest: AppInfoUseCaseTest)

    fun inject(cityUseCaseTest: CityUseCaseTest)

    fun inject(weatherUseCaseTest: WeatherUseCaseTest)

}