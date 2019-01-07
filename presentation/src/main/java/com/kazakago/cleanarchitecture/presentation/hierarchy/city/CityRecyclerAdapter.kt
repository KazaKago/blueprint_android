package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.global.adapter.AbsItemViewHolder
import kotlinx.android.synthetic.main.recycler_city.*

class CityRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<CityRecyclerAdapter.ViewHolder>() {

    var cityList: List<City> = listOf()
    var onItemClick: ((city: City) -> Unit)? = null

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
            containerView.setOnClickListener {
                item?.let { onItemClick?.invoke(it) }
            }
        }

        override fun onBind(item: City?) {
            cityTextView.text = item?.name
        }

    }

}
