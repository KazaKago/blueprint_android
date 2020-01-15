package com.kazakago.cleanarchitecture.presentation.view.hierarchy.city

import androidx.annotation.LayoutRes
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.view.R
import com.kazakago.cleanarchitecture.presentation.view.databinding.RecyclerCityBinding
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder

data class CityRecyclerItem(private val city: City) : Item<ViewHolder>(city.hashCode().toLong()) {

    var onClickItem: ((city: City) -> Unit)? = null

    @LayoutRes
    override fun getLayout(): Int {
        return R.layout.recycler_city
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val binding = RecyclerCityBinding.bind(viewHolder.root)
        binding.root.setOnClickListener {
            onClickItem?.invoke(city)
        }
        binding.cityTextView.text = city.name
    }

}
