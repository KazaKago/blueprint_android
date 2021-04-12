package com.kazakago.cleanarchitecture.presentation.view.hierarchy.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.kazakago.cleanarchitecture.presentation.view.R
import com.kazakago.cleanarchitecture.presentation.view.databinding.ActivityAboutBinding
import com.kazakago.cleanarchitecture.presentation.viewmodel.global.extension.toUri
import com.kazakago.cleanarchitecture.presentation.viewmodel.hierarchy.about.AboutViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class AboutActivity : AppCompatActivity() {

    class Contract : ActivityResultContract<Unit, ActivityResult>() {
        override fun createIntent(context: Context, input: Unit) = Intent(context, AboutActivity::class.java)
        override fun parseResult(resultCode: Int, intent: Intent?) = ActivityResult(resultCode, intent)
    }

    private val viewBinding by lazy { ActivityAboutBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<AboutViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.toolbar)

        viewBinding.playStoreLayout.setOnClickListener {
            viewModel.appInfo.value?.let { openActionView(it.playStoreUri.toUri()) }
        }
        viewBinding.webSiteLayout.setOnClickListener {
            viewModel.developerInfo.value?.let { openActionView(it.siteUrl.toUri()) }
        }
        viewBinding.mailLayout.setOnClickListener {
            viewModel.developerInfo.value?.let { openSendTo(it.mailAddress.toURI().toUri()) }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.appInfo.collect {
                if (it != null) viewBinding.versionTextView.text = getString(R.string.about_ver, it.versionName.value)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.developerInfo.collect {
                if (it != null) {
                    viewBinding.copyrightTextView.text = getString(R.string.about_copyright, Calendar.getInstance().get(Calendar.YEAR), it.name)
                    viewBinding.developByTextView.text = getString(R.string.about_develop_by, it.name)
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.isLoading.collect {
                if (it) viewBinding.loadingProgressBar.show() else viewBinding.loadingProgressBar.hide()
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.showError.collect {
                showExceptionSnackbar(it)
            }
        }
    }

    private fun showExceptionSnackbar(exception: Exception) {
        Snackbar.make(viewBinding.root, exception.localizedMessage ?: "", Snackbar.LENGTH_LONG).show()
    }

    private fun openActionView(uri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun openSendTo(uri: Uri) {
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        startActivity(intent)
    }
}
