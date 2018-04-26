package com.kazakago.cleanarchitecture.presentation.di

import com.kazakago.cleanarchitecture.presentation.hierarchy.about.AboutActivityViewModel
import com.kazakago.cleanarchitecture.presentation.hierarchy.about.AboutFragmentViewModel
import com.kazakago.cleanarchitecture.presentation.hierarchy.city.CityListActivityViewModel
import com.kazakago.cleanarchitecture.presentation.hierarchy.city.CityListFragmentViewModel
import com.kazakago.cleanarchitecture.presentation.hierarchy.forecast.ForecastActivityViewModel
import com.kazakago.cleanarchitecture.presentation.hierarchy.forecast.ForecastFragmentViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val presentationModule = applicationContext {
    viewModel { CityListActivityViewModel(get()) }
    viewModel { CityListFragmentViewModel(get(), get()) }
    viewModel { ForecastActivityViewModel(get(), it["city"]) }
    viewModel { ForecastFragmentViewModel(get(), get(), it["city"]) }
    viewModel { AboutActivityViewModel(get()) }
    viewModel { AboutFragmentViewModel(get(), get(), get()) }
}