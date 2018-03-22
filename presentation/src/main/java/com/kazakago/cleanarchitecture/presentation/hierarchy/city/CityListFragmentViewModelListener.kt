package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import com.kazakago.cleanarchitecture.domain.model.city.City

interface CityListFragmentViewModelListener {

    fun showToast(message: String?)

    fun toForecastActivity(city: City)

}
