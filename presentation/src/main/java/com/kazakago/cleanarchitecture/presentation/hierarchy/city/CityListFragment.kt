package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.NonNullObserver
import com.kazakago.cleanarchitecture.presentation.hierarchy.forecast.ForecastActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.fragment_city_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CityListFragment : Fragment() {

    companion object {
        fun createInstance(): CityListFragment {
            return CityListFragment()
        }
    }

    private val viewModel by sharedViewModel<CityListViewModel>()
    private val cityRecyclerAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_city_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityRecyclerView.adapter = cityRecyclerAdapter

        viewModel.cityList.observe(this, NonNullObserver {
            updateCityList(it)
        })
        viewModel.showToast.observe(this, "", NonNullObserver {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun updateCityList(cityList: List<City>) {
        cityRecyclerAdapter.updateAsync(cityList.map {
            CityRecyclerItem(it).apply {
                onClickItem = { city ->
                    goForecastActivity(city)
                }
            }
        })
    }

    private fun goForecastActivity(city: City) {
        val intent = ForecastActivity.createIntent(requireActivity(), city)
        startActivity(intent)
    }

}
