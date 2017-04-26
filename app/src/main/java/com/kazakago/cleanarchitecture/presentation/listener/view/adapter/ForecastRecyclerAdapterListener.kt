package com.kazakago.cleanarchitecture.presentation.listener.view.adapter

import com.kazakago.cleanarchitecture.presentation.presenter.adapter.ForecastViewModel

/**
 * Forecast RecyclerAdapter Listener
 *
 * Created by tamura_k on 2016/06/01.
 */
interface ForecastRecyclerAdapterListener {

    fun onItemClick(forecastViewModel: ForecastViewModel)

}
