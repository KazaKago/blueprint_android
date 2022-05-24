package com.kazakago.blueprint.presentation.controller.hierarchy.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kazakago.blueprint.presentation.ui.global.theme.AppTheme
import com.kazakago.blueprint.presentation.ui.hierarchy.about.AboutScreen
import com.kazakago.blueprint.presentation.viewmodel.hierarchy.about.AboutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutActivity : ComponentActivity() {

    class Contract : ActivityResultContract<Unit, ActivityResult>() {
        override fun createIntent(context: Context, input: Unit) = Intent(context, AboutActivity::class.java)
        override fun parseResult(resultCode: Int, intent: Intent?) = ActivityResult(resultCode, intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: AboutViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsState()
            AppTheme {
                AboutScreen(
                    uiState = uiState,
                    onClickBack = ::finish,
                    onClickWebSite = { openActionView(it.toString()) },
                    onClickMail = { openSendTo(it.toString()) },
                )
            }
        }
    }

    private fun openActionView(uri: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)
    }

    private fun openSendTo(uri: String) {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(uri))
        startActivity(intent)
    }
}
