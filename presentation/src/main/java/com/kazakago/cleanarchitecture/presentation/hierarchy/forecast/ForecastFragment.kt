package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.NonNullObserver
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.fragment_forecast.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ForecastFragment : Fragment() {

    companion object {
        fun createInstance(): ForecastFragment {
            return ForecastFragment()
        }
    }

    private val viewModel by sharedViewModel<ForecastViewModel>()
    private val forecastRecyclerAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        descriptionButton.setOnClickListener {
            showForecastDescriptionDialog()
        }
        forecastRecyclerView.adapter = forecastRecyclerAdapter

        viewModel.showToast.observe(this, "", NonNullObserver {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
        viewModel.weather.observe(this, NonNullObserver {
            updateWeather(it)
        })
        viewModel.isLoading.observe(this, NonNullObserver {
            if (it) loadingProgressBar.show() else loadingProgressBar.hide()
        })
    }

    private fun updateWeather(weather: Weather) {
        forecastRecyclerAdapter.updateAsync(mutableListOf<Group>().apply {
            add(ForecastRecyclerSummary(weather))
            addAll(weather.forecasts.map {
                ForecastRecyclerContent(it).apply {
                    onClickItem = { forecast ->
                        viewModel.onClickForecast(forecast)
                    }
                }
            })
        })
    }

    private fun showForecastDescriptionDialog() {
        val dialog = ForecastDescriptionDialog.createInstance()
        dialog.show(fragmentManager, "")
    }

}
