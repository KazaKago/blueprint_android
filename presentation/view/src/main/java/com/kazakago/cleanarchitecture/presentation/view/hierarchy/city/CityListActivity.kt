package com.kazakago.cleanarchitecture.presentation.view.hierarchy.city

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.City
import com.kazakago.cleanarchitecture.presentation.view.R
import com.kazakago.cleanarchitecture.presentation.view.databinding.ActivityCityListBinding
import com.kazakago.cleanarchitecture.presentation.view.hierarchy.about.AboutActivity
import com.kazakago.cleanarchitecture.presentation.view.hierarchy.about.OssLicensesMenuActivityContract
import com.kazakago.cleanarchitecture.presentation.view.hierarchy.forecast.ForecastActivity
import com.kazakago.cleanarchitecture.presentation.viewmodel.hierarchy.city.CityListViewModel
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityListActivity : AppCompatActivity() {

    private val cityRecyclerAdapter = GroupieAdapter()
    private val viewBinding by lazy { ActivityCityListBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<CityListViewModel>()
    private val forecastActivityLauncher = registerForActivityResult(ForecastActivity.Contract()) {}
    private val aboutActivityLauncher = registerForActivityResult(AboutActivity.Contract()) {}
    private val ossLicensesMenuActivityLauncher = registerForActivityResult(OssLicensesMenuActivityContract()) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.toolbar)

        viewBinding.cityRecyclerView.adapter = cityRecyclerAdapter

        lifecycleScope.launchWhenStarted {
            viewModel.cityList.collect {
                updateCityList(it)
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
        menuInflater.inflate(R.menu.city_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                goAbout()
                return true
            }
            R.id.action_licenses -> {
                goOssLicenses()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateCityList(cityList: List<City>) {
        cityRecyclerAdapter.updateAsync(cityList.map {
            CityItem(it).apply {
                onClickItem = { city ->
                    goForecast(city)
                }
            }
        })
    }

    private fun showExceptionSnackbar(exception: Exception) {
        Snackbar.make(viewBinding.root, exception.localizedMessage ?: "", Snackbar.LENGTH_LONG).show()
    }

    private fun goForecast(city: City) {
        forecastActivityLauncher.launch(city)
    }

    private fun goAbout() {
        aboutActivityLauncher.launch(Unit)
    }

    private fun goOssLicenses() {
        ossLicensesMenuActivityLauncher.launch(Unit)
    }
}
