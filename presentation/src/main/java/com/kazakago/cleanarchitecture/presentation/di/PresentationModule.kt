package com.kazakago.cleanarchitecture.presentation.di

import com.kazakago.cleanarchitecture.presentation.hierarchy.about.AboutViewModel
import com.kazakago.cleanarchitecture.presentation.hierarchy.city.CityListViewModel
import com.kazakago.cleanarchitecture.presentation.hierarchy.forecast.ForecastViewModel
import org.koin.android.architecture.ext.koin.viewModel
import org.koin.dsl.module.module

val presentationModule = module {
    viewModel { CityListViewModel(get(), get()) }
    viewModel { ForecastViewModel(get(), get(), it.component1()) }
    viewModel { AboutViewModel(get(), get(), get()) }
}