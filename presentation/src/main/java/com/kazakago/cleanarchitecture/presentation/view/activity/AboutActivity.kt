package com.kazakago.cleanarchitecture.presentation.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.listener.activity.AboutActivityViewModelListener
import com.kazakago.cleanarchitecture.presentation.presenter.activity.AboutActivityViewModel
import com.kazakago.cleanarchitecture.presentation.view.fragment.AboutFragment
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity(), AboutActivityViewModelListener {

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, AboutActivity::class.java)
    }

    private lateinit var viewModel: AboutActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        viewModel = ViewModelProviders.of(this, AboutActivityViewModel.Factory(application)).get(AboutActivityViewModel::class.java)
        viewModel.listener = this

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            replaceAboutFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.listener = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> viewModel.onClickBackIcon()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceAboutFragment() {
        val fragment = AboutFragment.createInstance()

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    /* AboutActivityViewModelListener */

}
