package com.kazakago.cleanarchitecture.presentation.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.extension.formattedDateText
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_forecast.view.*

class ForecastRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<ForecastRecyclerAdapter.ViewHolder>() {

    interface Listener {
        fun onItemClick(forecast: Forecast)
    }

    var listener: Listener? = null
    var forecastList: List<Forecast> = listOf()

    override fun getItemCount(): Int {
        return forecastList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item = forecastList[position]
    }

    inner class ViewHolder(context: Context, parent: ViewGroup) : AbsItemViewHolder<Forecast>(context, parent, R.layout.recycler_forecast) {
        init {
            itemView.setOnClickListener {
                item?.let { listener?.onItemClick(it) }
            }
        }
        override fun onSetItem(item: Forecast?) {
            Picasso.with(context).load(item?.imageUrl.toString())
                    .into(itemView.weatherImageView)
            itemView.dateLabelTextView.text = item?.dateLabel
            itemView.dateTextView.text = item?.date?.formattedDateText(context)
            itemView.telopTextView.text = item?.telop
            itemView.maxTemperatureTextView.text = context.getString(R.string.temperature_max, item?.maxTemperature?.toString() ?: "--")
            itemView.minTemperatureTextView.text = context.getString(R.string.temperature_min, item?.minTemperature?.toString() ?: "--")
        }
    }

}
