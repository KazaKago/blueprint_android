package com.kazakago.cleanarchitecture.presentation.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.R

class CityRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<CityRecyclerAdapter.ViewHolder>() {

    interface Listener {
        fun onItemClick(city: City)
    }

    inner class ViewHolder(context: Context, parent: ViewGroup) : AbsViewHolder<City>(context, parent, R.layout.recycler_city) {

        private val cityTextView = itemView.findViewById<TextView>(R.id.cityTextView)

        override fun setItem(item: City) {
            cityTextView.text = item.name
            itemView?.setOnClickListener {
                listener?.onItemClick(item)
            }
        }

    }

    var listener: Listener? = null
    var cityList: List<City> = listOf()

    override fun getItemCount(): Int = cityList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(cityList[position])
    }

}
