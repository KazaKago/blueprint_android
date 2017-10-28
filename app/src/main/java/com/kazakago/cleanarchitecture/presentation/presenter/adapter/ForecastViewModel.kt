package com.kazakago.cleanarchitecture.presentation.presenter.adapter

import android.content.Context
import android.databinding.ObservableField
import com.kazakago.cleanarchitecture.R
import com.kazakago.cleanarchitecture.domain.model.weather.ForecastModel

class ForecastViewModel(private val context: Context, forecast: ForecastModel) {

    val dateLabel = ObservableField<String>(forecast.dateLabel)
    val date = ObservableField<String>(forecast.date)
    val telop = ObservableField<String>(forecast.telop)
    val maxTemperature = ObservableField<String>(context.getString(R.string.temperature_max, forecast.temperature.max.celsius.toString()))
    val minTemperature = ObservableField<String>(context.getString(R.string.temperature_min, forecast.temperature.min.celsius.toString()))
    val imageUrl = ObservableField<String>(forecast.image.url)

}
