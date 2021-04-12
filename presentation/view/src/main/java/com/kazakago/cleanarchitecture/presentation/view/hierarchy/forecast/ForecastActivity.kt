package com.kazakago.cleanarchitecture.presentation.view.hierarchy.forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.City
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId
import com.kazakago.cleanarchitecture.domain.model.hierarchy.weather.Forecast
import com.kazakago.cleanarchitecture.domain.model.hierarchy.weather.Weather
import com.kazakago.cleanarchitecture.presentation.view.R
import com.kazakago.cleanarchitecture.presentation.view.databinding.ActivityForecastBinding
import com.kazakago.cleanarchitecture.presentation.viewmodel.hierarchy.forecast.ForecastViewModel
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ForecastActivity : AppCompatActivity() {

    class Contract : ActivityResultContract<City, ActivityResult>() {
        override fun createIntent(context: Context, input: City) = Intent(context, ForecastActivity::class.java).apply {
            putExtra(ParameterKey.CITY.name, input)
        }

        override fun parseResult(resultCode: Int, intent: Intent?) = ActivityResult(resultCode, intent)
    }

    private enum class ParameterKey {
        CITY,
    }

    private val forecastRecyclerAdapter = GroupieAdapter()
    private val viewBinding by lazy { ActivityForecastBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<ForecastViewModel> {
        val city = intent.getSerializableExtra(ParameterKey.CITY.name) as City
        parametersOf(city.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.toolbar)

        viewBinding.descriptionButton.setOnClickListener {
            viewModel.city.value?.let { showForecastDescriptionDialog(it.id) }
        }
        viewBinding.forecastRecyclerView.adapter = forecastRecyclerAdapter

        lifecycleScope.launchWhenStarted {
            viewModel.weather.collect {
                if (it != null) updateWeather(it)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.isLoading.collect {
                if (it) viewBinding.loadingProgressBar.show() else viewBinding.loadingProgressBar.hide()
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.showError.collect {
                showExceptionSnackbar(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.forecast, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> {
                viewModel.requestWeather()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateWeather(weather: Weather) {
        forecastRecyclerAdapter.updateAsync(
            listOf(ForecastSummaryItem(weather)) + weather.forecasts.map {
                ForecastContentItem(it).apply {
                    onClickItem = ::showForecastSnackbar
                }
            }
        )
    }

    private fun showExceptionSnackbar(exception: Exception) {
        Snackbar.make(viewBinding.root, exception.localizedMessage ?: "", Snackbar.LENGTH_LONG).show()
    }

    private fun showForecastSnackbar(forecast: Forecast) {
        Snackbar.make(viewBinding.root, forecast.telop, Snackbar.LENGTH_SHORT).show()
    }

    private fun showForecastDescriptionDialog(cityId: CityId) {
        val dialog = ForecastDescriptionDialog.createInstance(cityId)
        dialog.show(supportFragmentManager, "")
    }
}
