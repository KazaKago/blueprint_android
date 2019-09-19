package com.kazakago.cleanarchitecture.presentation.hierarchy.about

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.databinding.ActivityAboutBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, AboutActivity::class.java)
        }
    }

    private val viewModel by viewModel<AboutViewModel>()
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            replaceAboutFragment()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceAboutFragment() {
        supportFragmentManager.commit {
            val fragment = AboutFragment.createInstance()
            replace(R.id.fragmentContainer, fragment)
        }
    }

}
