package com.kazakago.blueprint.ui.global.entrypoint

import androidx.compose.runtime.Composable
import com.kazakago.blueprint.ui.global.di.DIProvider
import com.kazakago.blueprint.ui.global.navigation.MainNavHost
import com.kazakago.blueprint.ui.global.theme.AppTheme

@Composable
fun App() {
    DIProvider {
        AppTheme {
            MainNavHost()
        }
    }
}
