package com.kazakago.cleanarchitecture.presentation.viewmodel.di

import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId
import com.kazakago.cleanarchitecture.presentation.viewmodel.hierarchy.about.AboutViewModel
import com.kazakago.cleanarchitecture.presentation.viewmodel.hierarchy.city.CityListViewModel
import com.kazakago.cleanarchitecture.presentation.viewmodel.hierarchy.forecast.ForecastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { CityListViewModel(get(), get()) }
    viewModel { (cityId: CityId) -> ForecastViewModel(get(), get(), get(), cityId) }
    viewModel { AboutViewModel(get(), get()) }
}
