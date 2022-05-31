package com.kazakago.blueprint.presentation.controller.hierarchy.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.kazakago.blueprint.presentation.ui.global.theme.AppTheme
import com.kazakago.blueprint.presentation.ui.hierarchy.about.AboutScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.net.URI
import java.net.URL

@Destination
@Composable
fun AboutController(navigator: DestinationsNavigator) {
    val viewModel: AboutViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    AppTheme {
        AboutScreen(
            uiState = uiState,
            onClickBack = navigator::popBackStack,
            onClickWebSite = { actionView(context, it) },
            onClickMail = { sendTo(context, it.toURI()) },
        )
    }
}

private fun actionView(context: Context, url: URL) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url.toString()))
    context.startActivity(intent)
}

private fun sendTo(context: Context, uri: URI) {
    val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(uri.toString()))
    context.startActivity(intent)
}
