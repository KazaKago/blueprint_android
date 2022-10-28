package com.kazakago.blueprint.presentation.global.util

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.net.URI
import java.net.URL

@Composable
fun useActionView(): (URL) -> Unit {
    val context = LocalContext.current
    return {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.toString()))
        context.startActivity(intent)
    }
}

@Composable
fun useSendTo(): (URI) -> Unit {
    val context = LocalContext.current
    return {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(it.toString()))
        context.startActivity(intent)
    }
}
