package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.hierarchy.forecast.ForecastActivity
import kotlinx.android.synthetic.main.fragment_city_list.*
import org.koin.android.architecture.ext.viewModel

class CityListFragment : Fragment() {

    companion object {
        fun createInstance(): CityListFragment {
            return CityListFragment()
        }
    }

    private val viewModel by viewModel<CityListFragmentViewModel>()
    private lateinit var cityRecyclerAdapter: CityRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_city_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityRecyclerAdapter = CityRecyclerAdapter(requireActivity())
        cityRecyclerAdapter.listener = viewModel
        cityRecyclerView.adapter = cityRecyclerAdapter

        viewModel.cityList.observe(this, Observer {
            cityRecyclerAdapter.cityList = it ?: listOf()
            cityRecyclerAdapter.notifyDataSetChanged()
        })
        viewModel.showToast.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
        viewModel.toForecast.observe(this, Observer {
            it?.let { toForecastActivity(it) }
        })
    }

    private fun toForecastActivity(city: City) {
        val intent = ForecastActivity.createIntent(requireActivity(), city)
        startActivity(intent)
    }

}
