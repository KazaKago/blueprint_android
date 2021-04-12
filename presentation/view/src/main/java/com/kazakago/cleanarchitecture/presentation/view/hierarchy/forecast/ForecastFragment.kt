package com.kazakago.cleanarchitecture.presentation.view.hierarchy.forecast

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId
import com.kazakago.cleanarchitecture.domain.model.hierarchy.weather.Forecast
import com.kazakago.cleanarchitecture.domain.model.hierarchy.weather.Weather
import com.kazakago.cleanarchitecture.presentation.view.R
import com.kazakago.cleanarchitecture.presentation.view.databinding.FragmentForecastBinding
import com.kazakago.cleanarchitecture.presentation.viewmodel.hierarchy.forecast.ForecastViewModel
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ForecastFragment : Fragment() {

    private val forecastRecyclerAdapter = GroupieAdapter()
    private val args: ForecastFragmentArgs by navArgs()
    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<ForecastViewModel> {
        parametersOf(args.cityId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.descriptionButton.setOnClickListener {
            viewModel.city.value?.let { showForecastDescriptionDialog(it.id) }
        }
        binding.forecastRecyclerView.adapter = forecastRecyclerAdapter

        lifecycleScope.launchWhenStarted {
            viewModel.weather.collect {
                if (it != null) updateWeather(it)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.isLoading.collect {
                if (it) binding.loadingProgressBar.show() else binding.loadingProgressBar.hide()
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.showError.collect {
                showExceptionSnackbar(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.forecast, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> viewModel.requestWeather()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateWeather(weather: Weather) {
        forecastRecyclerAdapter.updateAsync(
            listOf(ForecastRecyclerSummary(weather)) + weather.forecasts.map {
                ForecastRecyclerContent(it).apply {
                    onClickItem = ::showForecastSnackbar
                }
            }
        )
    }

    private fun showExceptionSnackbar(exception: Exception) {
        Snackbar.make(binding.root, exception.localizedMessage ?: "", Snackbar.LENGTH_LONG).show()
    }

    private fun showForecastSnackbar(forecast: Forecast) {
        Snackbar.make(binding.root, forecast.telop, Snackbar.LENGTH_SHORT).show()
    }

    private fun showForecastDescriptionDialog(cityId: CityId) {
        val action = ForecastFragmentDirections.actionForecastFragmentToForecastDescriptionDialog(cityId)
        findNavController().navigate(action)
    }
}
