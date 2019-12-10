package com.kazakago.cleanarchitecture.view.hierarchy.city

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.kazakago.cleanarchitecture.view.R
import com.kazakago.cleanarchitecture.view.databinding.ActivityCityListBinding
import com.kazakago.cleanarchitecture.view.hierarchy.about.AboutActivity
import com.kazakago.cleanarchitecture.viewmodel.hierarchy.city.CityListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityListActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, CityListActivity::class.java)
        }
    }

    private val viewModel by viewModel<CityListViewModel>()
    private lateinit var binding: ActivityCityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

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
            R.id.action_about -> goAboutActivity()
            R.id.action_licenses -> goOssLicensesActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceCityListFragment() {
        supportFragmentManager.commit {
            val fragment = CityListFragment.createInstance()
            replace(R.id.fragmentContainer, fragment)
        }
    }

    private fun goAboutActivity() {
        val intent = AboutActivity.createIntent(this)
        startActivity(intent)
    }

    private fun goOssLicensesActivity() {
        val intent = Intent(this, OssLicensesMenuActivity::class.java)
        startActivity(intent)
    }

}