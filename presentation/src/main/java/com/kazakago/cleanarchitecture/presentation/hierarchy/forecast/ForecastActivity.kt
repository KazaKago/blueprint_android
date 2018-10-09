package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.extension.StringKey
import com.kazakago.cleanarchitecture.presentation.extension.value
import kotlinx.android.synthetic.main.activity_forecast.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ForecastActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context, city: City): Intent {
            val intent = Intent(context, ForecastActivity::class.java)
            intent.putExtra(RequestParameterKey.City.value(), city)
            return intent
        }
    }

    private enum class RequestParameterKey : StringKey {
        City
    }

    private val viewModel by viewModel<ForecastViewModel> {
        parametersOf(intent.getSerializableExtra(RequestParameterKey.City.value()) as City)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.finish.observe(this, "", Observer {
            finish()
        })
        viewModel.title.observe(this, Observer {
            title = it
        })

        if (savedInstanceState == null) {
            replaceForecastFragment()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> viewModel.onClickBackIcon()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceForecastFragment() {
        val fragment = ForecastFragment.createInstance()

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

}
