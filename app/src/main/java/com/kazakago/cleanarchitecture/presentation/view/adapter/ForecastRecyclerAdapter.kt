package com.kazakago.cleanarchitecture.presentation.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kazakago.cleanarchitecture.R
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.presentation.extension.formattedText
import com.squareup.picasso.Picasso

class ForecastRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<ForecastRecyclerAdapter.ViewHolder>() {

    interface Listener {
        fun onItemClick(forecast: Forecast)
    }

    inner class ViewHolder(context: Context, parent: ViewGroup) : AbsViewHolder<Forecast>(context, parent, R.layout.recycler_forecast) {

        private val dateLabelTextView = itemView.findViewById<TextView>(R.id.dateLabelTextView)
        private val dateTextView = itemView.findViewById<TextView>(R.id.dateTextView)
        private val weatherImageView = itemView.findViewById<ImageView>(R.id.weatherImageView)
        private val telopTextView = itemView.findViewById<TextView>(R.id.telopTextView)
        private val maxTemperatureTextView = itemView.findViewById<TextView>(R.id.maxTemperatureTextView)
        private val minTemperatureTextView = itemView.findViewById<TextView>(R.id.minTemperatureTextView)

        override fun setItem(item: Forecast) {
            Picasso.with(context).load(item.imageUrl)
                    .into(weatherImageView)
            dateLabelTextView.text = item.dateLabel
            dateTextView.text = item.date.formattedText(context)
            telopTextView.text = item.telop
            maxTemperatureTextView.text = context.getString(R.string.temperature_max, item.maxTemperature?.toString() ?: "--")
            minTemperatureTextView.text = context.getString(R.string.temperature_min, item.minTemperature?.toString() ?: "--")
            itemView?.setOnClickListener {
                listener?.onItemClick(item)
            }
        }

    }

    var listener: Listener? = null
    var forecastList: List<Forecast> = listOf()

    override fun getItemCount(): Int = forecastList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(forecastList[position])
    }

}
