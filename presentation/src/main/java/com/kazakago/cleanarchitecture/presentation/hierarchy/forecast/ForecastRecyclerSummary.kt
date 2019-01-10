package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.content.Context
import androidx.annotation.LayoutRes
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.global.extension.formattedDateTimeText
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.recycler_forecast_summary.*

data class ForecastRecyclerSummary(private val context: Context, private val weather: Weather) : Item(weather.hashCode().toLong()) {

    @LayoutRes
    override fun getLayout(): Int {
        return R.layout.recycler_forecast_summary
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.areaTextView.text = weather.location.area
        viewHolder.prefectureTextView.text = weather.location.prefecture
        viewHolder.cityTextView.text = weather.location.city
        viewHolder.publicTimeTextView.text = context.getString(R.string.public_time, weather.publicTime.formattedDateTimeText(context))
    }

}
