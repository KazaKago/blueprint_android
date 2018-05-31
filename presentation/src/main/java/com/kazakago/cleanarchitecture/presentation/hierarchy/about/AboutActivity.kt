package com.kazakago.cleanarchitecture.presentation.hierarchy.about

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.kazakago.cleanarchitecture.presentation.R
import kotlinx.android.synthetic.main.activity_about.*
import org.koin.android.architecture.ext.android.viewModel

class AboutActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, AboutActivity::class.java)
        }
    }

    private val viewModel by viewModel<AboutViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.finish.observe(this, "", Observer {
            finish()
        })

        if (savedInstanceState == null) {
            replaceAboutFragment()
        }
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

}
