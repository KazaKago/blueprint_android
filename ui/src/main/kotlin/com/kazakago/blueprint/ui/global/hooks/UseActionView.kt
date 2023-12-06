package com.kazakago.blueprint.ui.global.hooks

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.net.URL

@Composable
fun useActionView(): (URL) -> Unit {
    val context = LocalContext.current
    return remember {
        {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.toString()))
            context.startActivity(intent)
        }
    }
}
