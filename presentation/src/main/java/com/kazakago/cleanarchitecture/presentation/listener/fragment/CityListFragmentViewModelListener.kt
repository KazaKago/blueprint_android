package com.kazakago.cleanarchitecture.presentation.listener.fragment

import com.kazakago.cleanarchitecture.domain.model.city.City

interface CityListFragmentViewModelListener {

    fun showToast(message: String?)

    fun toForecastActivity(city: City)

}
