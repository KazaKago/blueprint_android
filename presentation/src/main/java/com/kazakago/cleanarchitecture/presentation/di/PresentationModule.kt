package com.kazakago.cleanarchitecture.presentation.di

import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.hierarchy.about.AboutViewModel
import com.kazakago.cleanarchitecture.presentation.hierarchy.city.CityListViewModel
import com.kazakago.cleanarchitecture.presentation.hierarchy.forecast.ForecastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { CityListViewModel(get(), get()) }
    viewModel { (city: City) -> ForecastViewModel(get(), get(), city) }
    viewModel { AboutViewModel(get(), get(), get()) }
}