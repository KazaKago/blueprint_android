package com.kazakago.cleanarchitecture.presentation.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.listener.fragment.CityListFragmentViewModelListener
import com.kazakago.cleanarchitecture.presentation.presenter.fragment.CityListFragmentViewModel
import com.kazakago.cleanarchitecture.presentation.view.activity.ForecastActivity
import com.kazakago.cleanarchitecture.presentation.view.adapter.CityRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_forecast.*

class CityListFragment : Fragment(), CityListFragmentViewModelListener {

    companion object {
        fun createInstance(): CityListFragment {
            return CityListFragment()
        }
    }

    private lateinit var viewModel: CityListFragmentViewModel
    private lateinit var cityRecyclerAdapter: CityRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, CityListFragmentViewModel.Factory(activity!!.application)).get(CityListFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_city_list, container, false)
        viewModel.listener = this
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityRecyclerAdapter = CityRecyclerAdapter(activity!!)
        cityRecyclerAdapter.listener = viewModel
        forecastRecyclerView.adapter = cityRecyclerAdapter

        viewModel.cityList.observe(this, Observer {
            cityRecyclerAdapter.cityList = it ?: listOf()
            cityRecyclerAdapter.notifyDataSetChanged()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.listener = null
    }

    /* CityListFragmentViewModelListener */

    override fun showToast(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun toForecastActivity(city: City) {
        val intent = ForecastActivity.createIntent(activity!!, city)
        startActivity(intent)
    }

}
