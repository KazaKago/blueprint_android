package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.observe
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.databinding.ActivityForecastBinding
import com.kazakago.cleanarchitecture.presentation.global.extension.StringKey
import com.kazakago.cleanarchitecture.presentation.global.extension.value
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ForecastActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context, city: City): Intent {
            val intent = Intent(context, ForecastActivity::class.java)
            intent.putExtra(ParameterKey.City.value(), city)
            return intent
        }
    }

    private enum class ParameterKey : StringKey {
        City
    }

    private val viewModel by viewModel<ForecastViewModel> {
        parametersOf(intent.getSerializableExtra(ParameterKey.City.value()) as City)
    }
    private lateinit var binding: ActivityForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.city.observe(this) {
            title = it.name
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

    private fun replaceForecastFragment() {
        supportFragmentManager.commit {
            val fragment = ForecastFragment.createInstance()
            replace(R.id.fragmentContainer, fragment)
        }
    }

}
