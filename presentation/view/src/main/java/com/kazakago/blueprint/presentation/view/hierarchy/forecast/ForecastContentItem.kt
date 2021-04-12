package com.kazakago.blueprint.presentation.view.hierarchy.forecast

import android.view.View
import coil.load
import com.kazakago.blueprint.domain.model.hierarchy.weather.Forecast
import com.kazakago.blueprint.presentation.view.R
import com.kazakago.blueprint.presentation.view.databinding.RecyclerForecastContentBinding
import com.kazakago.blueprint.presentation.view.global.extension.formattedText
import com.kazakago.blueprint.presentation.viewmodel.global.extension.toUri
import com.xwray.groupie.viewbinding.BindableItem

data class ForecastContentItem(private val forecast: Forecast) : BindableItem<RecyclerForecastContentBinding>(forecast.hashCode().toLong()) {

    var onClickItem: ((forecast: Forecast) -> Unit) = {}

    override fun getLayout() = R.layout.recycler_forecast_content

    override fun initializeViewBinding(view: View) = RecyclerForecastContentBinding.bind(view)

    override fun bind(viewBinding: RecyclerForecastContentBinding, position: Int) {
        viewBinding.root.setOnClickListener {
            onClickItem(forecast)
        }
        viewBinding.weatherImageView.load(forecast.imageUrl.toUri())
        viewBinding.dateLabelTextView.text = forecast.dateLabel
        viewBinding.dateTextView.text = forecast.date.formattedText(viewBinding.root.context)
        viewBinding.telopTextView.text = forecast.telop
        viewBinding.maxTemperatureTextView.text = viewBinding.root.context.getString(R.string.temperature_max, forecast.maxTemperature?.toString() ?: "--")
        viewBinding.minTemperatureTextView.text = viewBinding.root.context.getString(R.string.temperature_min, forecast.minTemperature?.toString() ?: "--")
    }
}
