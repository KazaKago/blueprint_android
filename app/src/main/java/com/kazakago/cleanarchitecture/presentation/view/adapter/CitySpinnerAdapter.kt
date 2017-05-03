package com.kazakago.cleanarchitecture.presentation.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val binding: SpinnerCityBinding?
        if (convertView != null) {
            binding = convertView.tag as? SpinnerCityBinding
        } else {
            binding = SpinnerCityBinding.inflate(LayoutInflater.from(context), parent, false)
            binding?.root?.tag = binding
        }

        binding?.viewModel = getItem(position)
        return binding?.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val binding: SpinnerCityDropdownBinding?
        if (convertView != null) {
            binding = convertView.tag as? SpinnerCityDropdownBinding
        } else {
            binding = SpinnerCityDropdownBinding.inflate(LayoutInflater.from(context), parent, false)
            binding?.root?.tag = binding
        }

        binding?.viewModel = getItem(position)
        return binding?.root
    }

}
