package com.kazakago.cleanarchitecture.presentation.view.hierarchy.forecast

import androidx.annotation.LayoutRes
import coil.api.load
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.presentation.view.R
import com.kazakago.cleanarchitecture.presentation.view.databinding.RecyclerForecastContentBinding
import com.kazakago.cleanarchitecture.presentation.view.global.extension.context
import com.kazakago.cleanarchitecture.presentation.view.global.extension.formattedText
import com.kazakago.cleanarchitecture.presentation.viewmodel.global.extension.toUri
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder

data class ForecastRecyclerContent(private val forecast: Forecast) : Item<ViewHolder>(forecast.hashCode().toLong()) {

    var onClickItem: ((forecast: Forecast) -> Unit)? = null

    @LayoutRes
    override fun getLayout(): Int {
        return R.layout.recycler_forecast_content
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val binding = RecyclerForecastContentBinding.bind(viewHolder.root)
        binding.root.setOnClickListener {
            onClickItem?.invoke(forecast)
        }
        binding.weatherImageView.load(forecast.imageUrl.toUri())
        binding.dateLabelTextView.text = forecast.dateLabel
        binding.dateTextView.text = forecast.date.formattedText(binding.context())
        binding.telopTextView.text = forecast.telop
        binding.maxTemperatureTextView.text = binding.context().getString(R.string.temperature_max, forecast.maxTemperature?.toString() ?: "--")
        binding.minTemperatureTextView.text = binding.context().getString(R.string.temperature_min, forecast.minTemperature?.toString() ?: "--")
    }

}