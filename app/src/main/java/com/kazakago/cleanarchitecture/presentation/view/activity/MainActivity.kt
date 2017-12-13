package com.kazakago.cleanarchitecture.presentation.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.kazakago.cleanarchitecture.R
import com.kazakago.cleanarchitecture.presentation.listener.presenter.activity.MainActivityViewModelListener
import com.kazakago.cleanarchitecture.presentation.listener.view.fragment.MainFragmentListener
import com.kazakago.cleanarchitecture.presentation.presenter.activity.MainActivityViewModel
import com.kazakago.cleanarchitecture.presentation.view.fragment.MainFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainFragmentListener, MainActivityViewModelListener {

    companion object {
        @JvmStatic
        fun createIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    private val viewModel by lazy { ViewModelProviders.of(this).get(MainActivityViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(viewModel)
        viewModel.listener = this

        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            replaceMainFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.listener = null
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> viewModel.onClickAboutMenu()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceMainFragment() {
        val fragment = MainFragment.createInstance()

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    /* MainFragmentListener */

    override fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }

    /* MainActivityViewModelListener */

    override fun toAboutActivity() {
        val intent = AboutActivity.createIntent(this)
        startActivity(intent)
    }

}
