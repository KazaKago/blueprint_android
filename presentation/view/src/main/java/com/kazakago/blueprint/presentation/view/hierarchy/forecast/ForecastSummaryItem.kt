package com.kazakago.blueprint.presentation.view.hierarchy.forecast

import android.view.View
import com.kazakago.blueprint.domain.model.hierarchy.weather.Weather
import com.kazakago.blueprint.presentation.view.R
import com.kazakago.blueprint.presentation.view.databinding.RecyclerForecastSummaryBinding
import com.kazakago.blueprint.presentation.view.global.extension.formattedText
import com.xwray.groupie.viewbinding.BindableItem

data class ForecastSummaryItem(private val weather: Weather) : BindableItem<RecyclerForecastSummaryBinding>(weather.cityId.value.hashCode().toLong()) {

    override fun getLayout() = R.layout.recycler_forecast_summary

    override fun initializeViewBinding(view: View) = RecyclerForecastSummaryBinding.bind(view)

    override fun bind(viewBinding: RecyclerForecastSummaryBinding, position: Int) {
        viewBinding.areaTextView.text = weather.location.area
        viewBinding.prefectureTextView.text = weather.location.prefecture
        viewBinding.cityTextView.text = weather.location.city
        viewBinding.publicTimeTextView.text = viewBinding.root.context.getString(R.string.public_time, weather.publicTime.formattedText(viewBinding.root.context))
    }
}
