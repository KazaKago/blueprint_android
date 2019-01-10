package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import androidx.annotation.LayoutRes
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.global.viewholder.ItemHolder
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.recycler_city.*

data class CityRecyclerItem(private val city: City) : ItemHolder(city.hashCode().toLong()) {

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
