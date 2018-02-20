package com.kazakago.cleanarchitecture.presentation.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.R
import kotlinx.android.synthetic.main.recycler_city.view.*

class CityRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<CityRecyclerAdapter.ViewHolder>() {

    interface Listener {
        fun onItemClick(city: City)
    }

    var listener: Listener? = null
    var cityList: List<City> = listOf()

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item = cityList[position]
    }

    inner class ViewHolder(context: Context, parent: ViewGroup) : AbsItemViewHolder<City>(context, parent, R.layout.recycler_city) {
        init {
            itemView.setOnClickListener {
                item?.let { listener?.onItemClick(it) }
            }
        }
        override fun onSetItem(item: City?) {
            itemView.cityTextView.text = item?.name
        }
    }

}
