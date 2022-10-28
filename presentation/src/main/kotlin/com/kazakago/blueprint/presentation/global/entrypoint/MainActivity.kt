package com.kazakago.blueprint.presentation.global.entrypoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kazakago.blueprint.presentation.global.theme.AppTheme
import com.kazakago.blueprint.presentation.hierarchy.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                DestinationsNavHost(NavGraphs.root)
            }
        }
    }
}
