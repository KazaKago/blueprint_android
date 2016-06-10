package com.weathercock.android_cleanarchitecture.presentation.listener.view.adapter;

import com.weathercock.android_cleanarchitecture.presentation.presenter.adapter.ForecastViewModel;

/**
 * Forecast RecyclerAdapter Listener
 * <p>
 * Created by tamura_k on 2016/06/01.
 */
public interface ForecastRecyclerAdapterListener {

    void onItemClick(ForecastViewModel forecastViewModel);

}
