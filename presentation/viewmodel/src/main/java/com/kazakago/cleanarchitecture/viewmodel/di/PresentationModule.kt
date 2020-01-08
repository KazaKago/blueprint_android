package com.kazakago.cleanarchitecture.viewmodel.di

import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.viewmodel.hierarchy.about.AboutViewModel
import com.kazakago.cleanarchitecture.viewmodel.hierarchy.city.CityListViewModel
import com.kazakago.cleanarchitecture.viewmodel.hierarchy.forecast.ForecastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { CityListViewModel(get(), get()) }
    viewModel { (cityId: CityId) -> ForecastViewModel(get(), get(), get(), cityId) }
    viewModel { AboutViewModel(get(), get(), get()) }
}