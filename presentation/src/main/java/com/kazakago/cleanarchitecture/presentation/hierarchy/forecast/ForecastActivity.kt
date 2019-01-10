package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.global.extension.StringKey
import com.kazakago.cleanarchitecture.presentation.global.extension.value
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.NonNullObserver
import kotlinx.android.synthetic.main.activity_forecast.*
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.city.observe(this, NonNullObserver {
            title = it.name
        })

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
        supportFragmentManager.transaction {
            val fragment = ForecastFragment.createInstance()
            replace(R.id.fragmentContainer, fragment)
        }
    }

}
