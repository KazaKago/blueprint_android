package com.kazakago.blueprint.presentation.viewmodel.hierarchy.about

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.kazakago.blueprint.domain.model.hierarchy.about.AppInfo
import com.kazakago.blueprint.domain.model.hierarchy.about.DeveloperInfo
import com.kazakago.blueprint.domain.model.hierarchy.about.Email
import java.net.URL

@Stable
sealed interface AboutUiState {
    object Loading : AboutUiState
    data class Completed(
        val appInfo: AppInfo,
        val developerInfo: DeveloperInfo,
    ) : AboutUiState

    fun developerMailAddress(invoke: (Email) -> Unit): () -> Unit = {
        when (this) {
            is Loading -> Unit
            is Completed -> invoke(developerInfo.mailAddress)
        }
    }

    fun developerSiteUrl(invoke: (URL) -> Unit): () -> Unit = {
        when (this) {
            is Loading -> Unit
            is Completed -> invoke(developerInfo.siteUrl)
        }
    }

    @Composable
    fun <T> classify(
        onLoading: @Composable (uiState: Loading) -> T,
        onCompleted: @Composable (uiState: Completed) -> T,
    ) = when (this) {
        is Loading -> onLoading(this)
        is Completed -> onCompleted(this)
    }
}
