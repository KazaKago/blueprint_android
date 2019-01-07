package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.global.adapter.AbsItemViewHolder
import com.kazakago.cleanarchitecture.presentation.global.extension.*
import kotlinx.android.synthetic.main.recycler_forecast_content.*
import kotlinx.android.synthetic.main.recycler_forecast_summary.*

class ForecastRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private enum class ViewType : IntKey {
        Summary,
        Content;
    }

    var weather: Weather? = null
    var onItemClick: ((forecast: Forecast) -> Unit)? = null

    override fun getItemCount(): Int {
        return weather?.let { it.forecasts.size + 1 } ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ViewType.Summary.value()
            else -> ViewType.Content.value()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (ViewType.values().resolve(viewType)) {
            ViewType.Summary -> SummaryViewHolder(context, parent)
            ViewType.Content -> ContentViewHolder(context, parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (ViewType.values().resolve(getItemViewType(position))) {
            ViewType.Summary -> onBindSummaryViewHolder(holder as SummaryViewHolder)
            ViewType.Content -> onBindContentViewHolder(holder as ContentViewHolder, position - 1)
        }
    }

    private fun onBindSummaryViewHolder(holder: SummaryViewHolder) {
        holder.item = weather
    }

    private fun onBindContentViewHolder(holder: ContentViewHolder, fixedIndex: Int) {
        holder.item = weather?.forecasts?.get(fixedIndex)
    }

    inner class SummaryViewHolder(context: Context, parent: ViewGroup) : AbsItemViewHolder<Weather>(context, parent, R.layout.recycler_forecast_summary) {

        override fun onBind(item: Weather?) {
            areaTextView.text = item?.location?.area
            prefectureTextView.text = item?.location?.prefecture
            cityTextView.text = item?.location?.city
            publicTimeTextView.text = context.getString(R.string.public_time, item?.publicTime?.formattedDateTimeText(context) ?: "")
        }
    }

    inner class ContentViewHolder(context: Context, parent: ViewGroup) : AbsItemViewHolder<Forecast>(context, parent, R.layout.recycler_forecast_content) {

        init {
            containerView.setOnClickListener {
                item?.let { onItemClick?.invoke(it) }
            }
        }

        override fun onBind(item: Forecast?) {
            weatherImageView.loadImageUrl(item?.imageUrl)
            dateLabelTextView.text = item?.dateLabel
            dateTextView.text = item?.date?.formattedDateText(context)
            telopTextView.text = item?.telop
            maxTemperatureTextView.text = context.getString(R.string.temperature_max, item?.maxTemperature?.toString() ?: "--")
            minTemperatureTextView.text = context.getString(R.string.temperature_min, item?.minTemperature?.toString() ?: "--")
        }
    }

}
