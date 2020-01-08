package com.kazakago.cleanarchitecture.view.hierarchy.forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.observe
import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreValue
import com.kazakago.cleanarchitecture.view.R
import com.kazakago.cleanarchitecture.view.databinding.ActivityForecastBinding
import com.kazakago.cleanarchitecture.view.global.extension.StringKey
import com.kazakago.cleanarchitecture.view.global.extension.value
import com.kazakago.cleanarchitecture.viewmodel.hierarchy.forecast.ForecastViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ForecastActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context, cityId: CityId): Intent {
            val intent = Intent(context, ForecastActivity::class.java)
            intent.putExtra(ParameterKey.CityId.value(), cityId)
            return intent
        }
    }

    private enum class ParameterKey : StringKey {
        CityId
    }

    private val viewModel by viewModel<ForecastViewModel> {
        parametersOf(intent.getSerializableExtra(ParameterKey.CityId.value()) as CityId)
    }
    private lateinit var binding: ActivityForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.city.observe(this) {
            updateCityValue(it.value)
        }

        if (savedInstanceState == null) {
            replaceForecastFragment()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateCityValue(cityValue: StoreValue<City>) {
        title = when (cityValue) {
            is StoreValue.Stored -> cityValue.value.name
            is StoreValue.NotStored -> ""
        }
    }

    private fun replaceForecastFragment() {
        supportFragmentManager.commit {
            val fragment = ForecastFragment.createInstance()
            replace(R.id.fragmentContainer, fragment)
        }
    }

}
