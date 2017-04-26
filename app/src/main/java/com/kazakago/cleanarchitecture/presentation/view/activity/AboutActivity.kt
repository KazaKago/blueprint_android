package com.kazakago.cleanarchitecture.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.kazakago.cleanarchitecture.R
import com.kazakago.cleanarchitecture.databinding.ActivityAboutBinding
import com.kazakago.cleanarchitecture.presentation.listener.presenter.activity.AboutActivityViewModelListener
import com.kazakago.cleanarchitecture.presentation.presenter.activity.AboutActivityViewModel
import com.kazakago.cleanarchitecture.presentation.view.fragment.AboutFragment

/**
 * About Activity
 *
 * @author Kensuke
 */
class AboutActivity : AppCompatActivity(), AboutActivityViewModelListener {

    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent {
            val intent = Intent(context, AboutActivity::class.java)
            return intent
        }
    }

    private lateinit var binding: ActivityAboutBinding
    private lateinit var viewModel: AboutActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityAboutBinding>(this, R.layout.activity_about)
        viewModel = AboutActivityViewModel(this)
        viewModel.listener = this
        binding.viewModel = viewModel

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            replaceAboutFragment()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> viewModel.onClickBackIcon()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceAboutFragment() {
        val fragment = AboutFragment.newInstance()

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_fragment, fragment)
        fragmentTransaction.commit()
    }

    /* AboutActivityViewModelListener */

    override fun onPerformFinish() {
        finish()
    }

}
