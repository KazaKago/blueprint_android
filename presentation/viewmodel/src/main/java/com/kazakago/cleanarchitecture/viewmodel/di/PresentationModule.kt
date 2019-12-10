package com.kazakago.cleanarchitecture.viewmodel.di

import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.viewmodel.hierarchy.about.AboutViewModel
import com.kazakago.cleanarchitecture.viewmodel.hierarchy.city.CityListViewModel
import com.kazakago.cleanarchitecture.viewmodel.hierarchy.forecast.ForecastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { CityListViewModel(get(), get()) }
    viewModel { (city: City) -> ForecastViewModel(get(), get(), city) }
    viewModel { AboutViewModel(get(), get(), get()) }
}