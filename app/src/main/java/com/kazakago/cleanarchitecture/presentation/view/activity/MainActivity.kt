package com.kazakago.cleanarchitecture.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.kazakago.cleanarchitecture.R
import com.kazakago.cleanarchitecture.databinding.ActivityMainBinding
import com.kazakago.cleanarchitecture.presentation.listener.presenter.activity.MainActivityViewModelListener
import com.kazakago.cleanarchitecture.presentation.listener.view.fragment.MainFragmentListener
import com.kazakago.cleanarchitecture.presentation.presenter.activity.MainActivityViewModel
import com.kazakago.cleanarchitecture.presentation.view.fragment.MainFragment

/**
 * Main Activity
 *
 * @author Kensuke
 */
class MainActivity : AppCompatActivity(), MainFragmentListener, MainActivityViewModelListener {

    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        viewModel = MainActivityViewModel(this)
        viewModel.listener = this
        binding.viewModel = viewModel

        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            replaceMainFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_about -> viewModel.onClickAboutMenu()
        }
        return super.onOptionsItemSelected(item)
    }

    fun replaceMainFragment() {
        val fragment = MainFragment.newInstance()

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_fragment, fragment)
        fragmentTransaction.commit()
    }

    /* MainFragmentListener */

    override fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }

    /* MainActivityViewModelListener */

    override fun toAboutActivity() {
        val intent = AboutActivity.newInstance(this)
        startActivity(intent)
    }

}
