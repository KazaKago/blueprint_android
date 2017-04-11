package com.kazakago.cleanarchitecture.presentation.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.kazakago.cleanarchitecture.R
import com.kazakago.cleanarchitecture.databinding.SpinnerCityBinding
import com.kazakago.cleanarchitecture.databinding.SpinnerCityDropdownBinding
import com.kazakago.cleanarchitecture.presentation.presenter.adapter.CityViewModel
import java.util.*

/**
 * City Spinner Adapter
 *
 * Created by tamura_k on 2016/05/31.
 */
class CitySpinnerAdapter(private val context: Context) : BaseAdapter() {

    var cityViewModelList: List<CityViewModel> = ArrayList()

    override fun getCount(): Int {
        return cityViewModelList.size
    }

    override fun getItem(position: Int): CityViewModel {
        return cityViewModelList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var binding: SpinnerCityBinding? = null
        val view: View = convertView?.let {
            binding = it.tag as? SpinnerCityBinding
            it
        } ?: run {
            binding = DataBindingUtil.inflate<SpinnerCityBinding>(LayoutInflater.from(context), R.layout.spinner_city, parent, false)
            val view = binding!!.root
            view.tag = binding
            view
        }

        binding?.viewModel = getItem(position)
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var binding: SpinnerCityDropdownBinding? = null
        val view: View = convertView?.let {
            binding = it.tag as? SpinnerCityDropdownBinding
            it
        } ?: run {
            binding = DataBindingUtil.inflate<SpinnerCityDropdownBinding>(LayoutInflater.from(context), R.layout.spinner_city_dropdown, parent, false)
            val view = binding!!.root
            view.tag = binding
            view
        }

        binding?.viewModel = getItem(position)
        return view
    }

}
