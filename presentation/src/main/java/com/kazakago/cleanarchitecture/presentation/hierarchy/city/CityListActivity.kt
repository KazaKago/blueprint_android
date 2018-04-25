package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.hierarchy.about.AboutActivity
import kotlinx.android.synthetic.main.activity_forecast.*

class CityListActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, CityListActivity::class.java)
        }
    }

    private lateinit var viewModel: CityListActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_list)
        viewModel = ViewModelProvider(this, CityListActivityViewModel.Factory(application)).get(CityListActivityViewModel::class.java)

        setSupportActionBar(toolbar)

        viewModel.toAbout.observe(this, Observer {
            toAboutActivity()
        })

        if (savedInstanceState == null) {
            replaceCityListFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.city_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> viewModel.onClickAboutMenu()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceCityListFragment() {
        val fragment = CityListFragment.createInstance()

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    private fun toAboutActivity() {
        val intent = AboutActivity.createIntent(this)
        startActivity(intent)
    }

}