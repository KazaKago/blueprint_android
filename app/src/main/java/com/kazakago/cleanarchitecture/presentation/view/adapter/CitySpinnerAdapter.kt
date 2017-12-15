package com.kazakago.cleanarchitecture.presentation.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.kazakago.cleanarchitecture.R
import com.kazakago.cleanarchitecture.domain.model.city.City

class CitySpinnerAdapter(private val context: Context) : BaseAdapter() {

    inner class ViewHolder(context: Context, parent: ViewGroup) : AbsViewHolder<City>(context, parent, R.layout.spinner_city) {

        private val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)

        override fun setItem(item: City) {
            nameTextView.text = item.name
        }
    }

    inner class DropDownViewHolder(context: Context, parent: ViewGroup) : AbsViewHolder<City>(context, parent, R.layout.spinner_city_dropdown) {

        private val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)

        override fun setItem(item: City) {
            nameTextView.text = item.name
        }
    }

    var cityList: List<City> = listOf()

    override fun getCount(): Int = cityList.size

    override fun getItem(position: Int): City = cityList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var mutableConvertView = convertView
        val viewHolder: ViewHolder
        if (mutableConvertView == null) {
            viewHolder = ViewHolder(context, parent)
            mutableConvertView = viewHolder.itemView
            mutableConvertView.tag = viewHolder
        } else {
            viewHolder = mutableConvertView.tag as ViewHolder
        }
        viewHolder.setItem(cityList[position])
        return mutableConvertView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var mutableConvertView = convertView
        val viewHolder: DropDownViewHolder
        if (mutableConvertView == null) {
            viewHolder = DropDownViewHolder(context, parent)
            mutableConvertView = viewHolder.itemView
            mutableConvertView.tag = viewHolder
        } else {
            viewHolder = mutableConvertView.tag as DropDownViewHolder
        }
        viewHolder.setItem(cityList[position])
        return mutableConvertView
    }

}
