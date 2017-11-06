package com.kazakago.cleanarchitecture.presentation.listener.view.adapter

import com.kazakago.cleanarchitecture.presentation.presenter.adapter.ForecastViewModel

interface ForecastRecyclerAdapterListener {

    fun onItemClick(forecastViewModel: ForecastViewModel)

}
