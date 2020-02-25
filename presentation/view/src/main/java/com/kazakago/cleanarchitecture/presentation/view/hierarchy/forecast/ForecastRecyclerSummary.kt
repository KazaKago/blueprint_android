package com.kazakago.cleanarchitecture.presentation.view.hierarchy.forecast

import androidx.annotation.LayoutRes
import com.kazakago.cleanarchitecture.domain.model.hierarchy.weather.Weather
import com.kazakago.cleanarchitecture.presentation.view.R
import com.kazakago.cleanarchitecture.presentation.view.databinding.RecyclerForecastSummaryBinding
import com.kazakago.cleanarchitecture.presentation.view.global.extension.context
import com.kazakago.cleanarchitecture.presentation.view.global.extension.formattedText
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

data class ForecastRecyclerSummary(private val weather: Weather) : Item<GroupieViewHolder>(weather.hashCode().toLong()) {

    @LayoutRes
    override fun getLayout(): Int {
        return R.layout.recycler_forecast_summary
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val binding = RecyclerForecastSummaryBinding.bind(viewHolder.root)
        binding.areaTextView.text = weather.location.area
        binding.prefectureTextView.text = weather.location.prefecture
        binding.cityTextView.text = weather.location.city
        binding.publicTimeTextView.text = binding.context().getString(R.string.public_time, weather.publicTime.formattedText(binding.context()))
    }

}
