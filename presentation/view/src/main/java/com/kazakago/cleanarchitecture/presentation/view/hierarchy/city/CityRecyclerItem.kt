package com.kazakago.cleanarchitecture.presentation.view.hierarchy.city

import android.view.View
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.City
import com.kazakago.cleanarchitecture.presentation.view.R
import com.kazakago.cleanarchitecture.presentation.view.databinding.RecyclerCityBinding
import com.xwray.groupie.viewbinding.BindableItem

data class CityRecyclerItem(private val city: City) : BindableItem<RecyclerCityBinding>(city.hashCode().toLong()) {

    var onClickItem: ((city: City) -> Unit) = {}

    override fun getLayout() = R.layout.recycler_city

    override fun initializeViewBinding(view: View) = RecyclerCityBinding.bind(view)

    override fun bind(viewBinding: RecyclerCityBinding, position: Int) {
        viewBinding.root.setOnClickListener {
            onClickItem(city)
        }
        viewBinding.cityTextView.text = city.name
    }
}
