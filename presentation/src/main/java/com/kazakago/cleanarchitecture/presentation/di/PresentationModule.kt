package com.kazakago.cleanarchitecture.presentation.di

import com.kazakago.cleanarchitecture.presentation.hierarchy.about.AboutViewModel
import com.kazakago.cleanarchitecture.presentation.hierarchy.city.CityListViewModel
import com.kazakago.cleanarchitecture.presentation.hierarchy.forecast.ForecastViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val presentationModule = applicationContext {
    viewModel { CityListViewModel(get(), get()) }
    viewModel { ForecastViewModel(get(), get(), it["city"]) }
    viewModel { AboutViewModel(get(), get(), get()) }
}