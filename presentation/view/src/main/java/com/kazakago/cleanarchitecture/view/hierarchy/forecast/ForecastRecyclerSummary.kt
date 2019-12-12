package com.kazakago.cleanarchitecture.view.hierarchy.forecast

import androidx.annotation.LayoutRes
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.view.R
import com.kazakago.cleanarchitecture.view.databinding.RecyclerForecastSummaryBinding
import com.kazakago.cleanarchitecture.view.global.extension.context
import com.kazakago.cleanarchitecture.view.global.extension.formattedText
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder

data class ForecastRecyclerSummary(private val weather: Weather) : Item<ViewHolder>(weather.hashCode().toLong()) {

    @LayoutRes
    override fun getLayout(): Int {
        return R.layout.recycler_forecast_summary
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val binding = RecyclerForecastSummaryBinding.bind(viewHolder.root)
        binding.areaTextView.text = weather.location.area
        binding.prefectureTextView.text = weather.location.prefecture
        binding.cityTextView.text = weather.location.city
        binding.publicTimeTextView.text = binding.context().getString(R.string.public_time, weather.publicTime.formattedText(binding.context()))
    }

}
