package com.kazakago.cleanarchitecture.presentation.listener.view.adapter

import com.kazakago.cleanarchitecture.domain.model.weather.ForecastModel

interface ForecastRecyclerAdapterListener {

    fun onItemClick(forecastModel: ForecastModel)

}
