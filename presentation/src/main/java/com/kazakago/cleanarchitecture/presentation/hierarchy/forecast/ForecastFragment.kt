package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.R
import kotlinx.android.synthetic.main.fragment_forecast.*
import org.koin.android.architecture.ext.getViewModel

class ForecastFragment : Fragment() {

    companion object {
        fun createInstance(city: City): ForecastFragment {
            val fragment = ForecastFragment()
            val bundle = Bundle()
            bundle.putSerializable(Key.City.name, city)
            fragment.arguments = bundle
            return fragment
        }
    }

    private enum class Key {
        City
    }

    private lateinit var viewModel: ForecastFragmentViewModel
    private lateinit var forecastRecyclerAdapter: ForecastRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val city = arguments?.getSerializable(Key.City.name) as City
        viewModel = getViewModel { mapOf("city" to city) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        forecastRecyclerAdapter = ForecastRecyclerAdapter(requireActivity())
        forecastRecyclerAdapter.listener = viewModel
        forecastRecyclerView.adapter = forecastRecyclerAdapter

        viewModel.showToast.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
        viewModel.weather.observe(this, Observer {
            forecastRecyclerAdapter.weather = it
            forecastRecyclerAdapter.notifyDataSetChanged()
        })
        viewModel.isLoading.observe(this, Observer {
            if (it == true) loadingProgressBar.show() else loadingProgressBar.hide()
        })
    }

}
