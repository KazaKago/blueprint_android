package com.kazakago.cleanarchitecture.presentation.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.kazakago.cleanarchitecture.R
import com.kazakago.cleanarchitecture.domain.model.city.CityModel

class CitySpinnerAdapter(private val context: Context) : BaseAdapter() {

    inner class ViewHolder(context: Context, parent: ViewGroup) : AbsViewHolder<CityModel>(context, parent, R.layout.spinner_city) {

        private val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)

        override fun setItem(item: CityModel) {
            nameTextView.text = item.name
        }
    }

    inner class DropDownViewHolder(context: Context, parent: ViewGroup) : AbsViewHolder<CityModel>(context, parent, R.layout.spinner_city_dropdown) {

        private val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)

        override fun setItem(item: CityModel) {
            nameTextView.text = item.name
        }
    }

    var cityModelList: List<CityModel> = listOf()

    override fun getCount(): Int = cityModelList.size

    override fun getItem(position: Int): CityModel = cityModelList[position]

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
        viewHolder.setItem(cityModelList[position])
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
        viewHolder.setItem(cityModelList[position])
        return mutableConvertView
    }

}
