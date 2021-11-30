package com.kazakago.blueprint.presentation.view.hierarchy.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kazakago.blueprint.presentation.view.R
import com.kazakago.blueprint.presentation.view.databinding.ActivityAboutBinding
import com.kazakago.blueprint.presentation.view.global.flow.collectOnStarted
import com.kazakago.blueprint.presentation.viewmodel.hierarchy.about.AboutViewModel
import dagger.hilt.android.AndroidEntryPoint
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewBinding.webSiteLayout.setOnClickListener {
            aboutViewModel.developerInfo.value?.let { openActionView(Uri.parse(it.siteUrl.toString())) }
        }
        viewBinding.mailLayout.setOnClickListener {
            aboutViewModel.developerInfo.value?.let { openSendTo(Uri.parse(it.mailAddress.toURI().toString())) }
        }
        viewBinding.ossLicenseLayout.setOnClickListener {
            goOssLicenses()
        }

        aboutViewModel.appInfo.collectOnStarted(this) {
            if (it != null) viewBinding.versionTextView.text = getString(R.string.about_ver, it.versionName.value, it.versionCode.value)
        }
        aboutViewModel.developerInfo.collectOnStarted(this) {
            if (it != null) {
                viewBinding.copyrightTextView.text = getString(R.string.about_copyright, Calendar.getInstance().get(Calendar.YEAR), it.name)
                viewBinding.developByTextView.text = getString(R.string.about_develop_by, it.name)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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
