package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.NonNullObserver
import kotlinx.android.synthetic.main.fragment_forecast.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ForecastFragment : Fragment() {

    companion object {
        fun createInstance(): ForecastFragment {
            return ForecastFragment()
        }
    }

    private val viewModel by sharedViewModel<ForecastViewModel>()
    private lateinit var forecastRecyclerAdapter: ForecastRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        forecastRecyclerAdapter = ForecastRecyclerAdapter(requireActivity())
        forecastRecyclerAdapter.onItemClick = {
            viewModel.onClickForecast(it)
        }
        forecastRecyclerView.adapter = forecastRecyclerAdapter

        viewModel.showToast.observe(this, "", NonNullObserver {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
        viewModel.weather.observe(this, Observer {
            forecastRecyclerAdapter.weather = it
            forecastRecyclerAdapter.notifyDataSetChanged()
        })
        viewModel.isLoading.observe(this, NonNullObserver {
            if (it) loadingProgressBar.show() else loadingProgressBar.hide()
        })
    }

}
