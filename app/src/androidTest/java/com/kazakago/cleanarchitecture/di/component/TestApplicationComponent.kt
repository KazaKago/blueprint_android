package com.kazakago.cleanarchitecture.di.component

import com.kazakago.cleanarchitecture.di.module.ApplicationModule
import com.kazakago.cleanarchitecture.di.module.DataModule
import com.kazakago.cleanarchitecture.di.module.DomainModule
import com.kazakago.cleanarchitecture.domain.usecase.AboutUseCaseTest
import com.kazakago.cleanarchitecture.domain.usecase.CityUseCaseTest
import com.kazakago.cleanarchitecture.domain.usecase.WeatherUseCaseTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, DomainModule::class, DataModule::class))
interface TestApplicationComponent {

    fun inject(aboutUseCaseTest: AboutUseCaseTest)

    fun inject(cityUseCaseTest: CityUseCaseTest)

    fun inject(weatherUseCaseTest: WeatherUseCaseTest)

}