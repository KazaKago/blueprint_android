package com.kazakago.cleanarchitecture.presentation.di

import android.app.Application
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.hierarchy.about.AboutViewModel
import com.kazakago.cleanarchitecture.presentation.hierarchy.city.CityListViewModel
import com.kazakago.cleanarchitecture.presentation.hierarchy.forecast.ForecastViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val presentationModule = module {
    single { androidContext().applicationContext as Application }
    viewModel { CityListViewModel(get(), get()) }
    viewModel { (city: City) -> ForecastViewModel(get(), get(), city) }
    viewModel { AboutViewModel(get(), get(), get()) }
}