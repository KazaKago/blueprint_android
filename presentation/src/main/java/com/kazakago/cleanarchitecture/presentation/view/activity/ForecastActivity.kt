package com.kazakago.cleanarchitecture.presentation.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.listener.activity.ForecastActivityViewModelListener
import com.kazakago.cleanarchitecture.presentation.view.fragment.ForecastFragment
import com.kazakago.cleanarchitecture.presentation.viewmodel.activity.ForecastActivityViewModel
import kotlinx.android.synthetic.main.activity_forecast.*

class ForecastActivity : AppCompatActivity(), ForecastActivityViewModelListener {

    companion object {
        fun createIntent(context: Context, city: City): Intent {
            val intent = Intent(context, ForecastActivity::class.java)
            intent.putExtra(Key.City.name, city)
            return intent
        }
    }

    private enum class Key {
        City
    }

    private lateinit var viewModel: ForecastActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
        val city = intent.getSerializableExtra(Key.City.name) as City
        viewModel = ViewModelProvider(this, ForecastActivityViewModel.Factory(application, city)).get(ForecastActivityViewModel::class.java)
        viewModel.listener = this

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.title.observe(this, Observer {
            title = it
        })

        if (savedInstanceState == null) {
            replaceForecastFragment(city)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> viewModel.onClickBackIcon()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.listener = null
    }

    private fun replaceForecastFragment(city: City) {
        val fragment = ForecastFragment.createInstance(city)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    //region ForecastActivityViewModelListener

    //endregion

}
