package com.kazakago.blueprint.presentation.view.hierarchy.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kazakago.blueprint.presentation.view.R
import com.kazakago.blueprint.presentation.view.databinding.ActivityAboutBinding
import com.kazakago.blueprint.presentation.viewmodel.hierarchy.about.AboutViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.util.*

@AndroidEntryPoint
class AboutActivity : AppCompatActivity() {

    class Contract : ActivityResultContract<Unit, ActivityResult>() {
        override fun createIntent(context: Context, input: Unit) = Intent(context, AboutActivity::class.java)
        override fun parseResult(resultCode: Int, intent: Intent?) = ActivityResult(resultCode, intent)
    }

    private val viewBinding by lazy { ActivityAboutBinding.inflate(layoutInflater) }
    private val ossLicensesMenuActivityLauncher = registerForActivityResult(OssLicensesMenuActivityContract()) {}
    private val aboutViewModel: AboutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.toolbar)

        viewBinding.playStoreLayout.setOnClickListener {
            aboutViewModel.appInfo.value?.let { openActionView(Uri.parse(it.playStoreUri.toString())) }
        }
        viewBinding.webSiteLayout.setOnClickListener {
            aboutViewModel.developerInfo.value?.let { openActionView(Uri.parse(it.siteUrl.toString())) }
        }
        viewBinding.mailLayout.setOnClickListener {
            aboutViewModel.developerInfo.value?.let { openSendTo(Uri.parse(it.mailAddress.toURI().toString())) }
        }
        viewBinding.ossLicenseLayout.setOnClickListener {
            goOssLicenses()
        }

        lifecycleScope.launchWhenStarted {
            aboutViewModel.appInfo.collect {
                if (it != null) viewBinding.versionTextView.text = getString(R.string.about_ver, it.versionName.value)
            }
        }
        lifecycleScope.launchWhenStarted {
            aboutViewModel.developerInfo.collect {
                if (it != null) {
                    viewBinding.copyrightTextView.text = getString(R.string.about_copyright, Calendar.getInstance().get(Calendar.YEAR), it.name)
                    viewBinding.developByTextView.text = getString(R.string.about_develop_by, it.name)
                }
            }
        }
    }

    private fun openActionView(uri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun openSendTo(uri: Uri) {
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        startActivity(intent)
    }

    private fun goOssLicenses() {
        ossLicensesMenuActivityLauncher.launch(Unit)
    }
}
