package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.extension.formattedDateText
import com.kazakago.cleanarchitecture.presentation.extension.formattedDateTimeText
import com.kazakago.cleanarchitecture.presentation.extension.toUri
import com.kazakago.cleanarchitecture.presentation.adapter.AbsItemViewHolder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_forecast_content.view.*
import kotlinx.android.synthetic.main.recycler_forecast_summary.view.*

class ForecastRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface Listener {
        fun onItemClick(forecast: Forecast)
    }

    private enum class ViewType {
        Summary,
        Content;
    }

    var weather: Weather? = null
    var listener: Listener? = null

    override fun getItemCount(): Int {
        return weather?.let { it.forecasts.size + 1 } ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ViewType.Summary.ordinal
            else -> ViewType.Content.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.Summary.ordinal -> SummaryViewHolder(context, parent)
            else -> ContentViewHolder(context, parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SummaryViewHolder -> onBindSummaryViewHolder(holder)
            is ContentViewHolder -> onBindContentViewHolder(holder, position - 1)
        }
    }

    private fun onBindSummaryViewHolder(holder: SummaryViewHolder) {
        holder.item = weather
    }

    private fun onBindContentViewHolder(holder: ContentViewHolder, index: Int) {
        holder.item = weather?.forecasts?.get(index)
    }

    inner class SummaryViewHolder(context: Context, parent: ViewGroup) : AbsItemViewHolder<Weather>(context, parent, R.layout.recycler_forecast_summary) {

        override fun onSetItem(item: Weather?) {
            itemView.areaTextView.text = item?.location?.area
            itemView.prefectureTextView.text = item?.location?.prefecture
            itemView.cityTextView.text = item?.location?.city
            itemView.publicTimeTextView.text = context.getString(R.string.public_time, item?.publicTime?.formattedDateTimeText(context) ?: "")
        }
    }

    inner class ContentViewHolder(context: Context, parent: ViewGroup) : AbsItemViewHolder<Forecast>(context, parent, R.layout.recycler_forecast_content) {

        init {
            itemView.setOnClickListener {
                item?.let { listener?.onItemClick(it) }
            }
        }

        override fun onSetItem(item: Forecast?) {
            Picasso.get().load(item?.imageUrl?.toUri())
                    .into(itemView.weatherImageView)
            itemView.dateLabelTextView.text = item?.dateLabel
            itemView.dateTextView.text = item?.date?.formattedDateText(context)
            itemView.telopTextView.text = item?.telop
            itemView.maxTemperatureTextView.text = context.getString(R.string.temperature_max, item?.maxTemperature?.toString() ?: "--")
            itemView.minTemperatureTextView.text = context.getString(R.string.temperature_min, item?.minTemperature?.toString() ?: "--")
        }
    }

}
