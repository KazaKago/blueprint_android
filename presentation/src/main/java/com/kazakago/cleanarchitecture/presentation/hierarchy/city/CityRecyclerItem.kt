package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.content.Context
import androidx.annotation.LayoutRes
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.recycler_city.*

data class CityRecyclerItem(private val context: Context, private val city: City) : Item(city.hashCode().toLong()) {

    var onClickItem: ((city: City) -> Unit)? = null

    @LayoutRes
    override fun getLayout(): Int {
        return R.layout.recycler_city
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.containerView.setOnClickListener {
            onClickItem?.invoke(city)
        }
        viewHolder.cityTextView.text = city.name
    }

}
