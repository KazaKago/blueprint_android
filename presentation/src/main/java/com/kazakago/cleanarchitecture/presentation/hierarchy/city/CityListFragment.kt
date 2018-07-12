package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.hierarchy.forecast.ForecastActivity
import com.kazakago.cleanarchitecture.presentation.livedata.nonnulllivedata.NonNullObserver
import kotlinx.android.synthetic.main.fragment_city_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CityListFragment : Fragment() {

    companion object {
        fun createInstance(): CityListFragment {
            return CityListFragment()
        }
    }

    private val viewModel by sharedViewModel<CityListViewModel>()
    private lateinit var cityRecyclerAdapter: CityRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_city_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityRecyclerAdapter = CityRecyclerAdapter(requireActivity())
        cityRecyclerAdapter.onItemClick = {
            viewModel.onClickCity(it)
        }
        cityRecyclerView.adapter = cityRecyclerAdapter

        viewModel.cityList.observe(this, NonNullObserver {
            cityRecyclerAdapter.cityList = it
            cityRecyclerAdapter.notifyDataSetChanged()
        })
        viewModel.showToast.observe(this, "", NonNullObserver {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
        viewModel.toForecast.observe(this, "", NonNullObserver {
            toForecastActivity(it)
        })
    }

    private fun toForecastActivity(city: City) {
        val intent = ForecastActivity.createIntent(requireActivity(), city)
        startActivity(intent)
    }

}
