package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import androidx.annotation.LayoutRes
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.global.extension.formattedDateText
import com.kazakago.cleanarchitecture.presentation.global.extension.loadImageUrl
import com.kazakago.cleanarchitecture.presentation.global.viewholder.ItemHolder
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.recycler_forecast_content.*

data class ForecastRecyclerContent(private val forecast: Forecast) : ItemHolder(forecast.hashCode().toLong()) {

    var onClickItem: ((forecast: Forecast) -> Unit)? = null

    @LayoutRes
    override fun getLayout(): Int {
        return R.layout.recycler_forecast_content
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.containerView.setOnClickListener {
            onClickItem?.invoke(forecast)
        }
        viewHolder.weatherImageView.loadImageUrl(forecast.imageUrl)
        viewHolder.dateLabelTextView.text = forecast.dateLabel
        viewHolder.dateTextView.text = forecast.date.formattedDateText(context)
        viewHolder.telopTextView.text = forecast.telop
        viewHolder.maxTemperatureTextView.text = context.getString(R.string.temperature_max, forecast.maxTemperature?.toString() ?: "--")
        viewHolder.minTemperatureTextView.text = context.getString(R.string.temperature_min, forecast.minTemperature?.toString() ?: "--")
    }

}