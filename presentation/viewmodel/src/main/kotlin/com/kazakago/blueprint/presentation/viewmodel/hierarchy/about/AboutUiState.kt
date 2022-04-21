package com.kazakago.blueprint.presentation.viewmodel.hierarchy.about

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import java.net.URI
import java.net.URL

@Stable
sealed interface AboutUiState {
    object Loading : AboutUiState
    data class Completed(
        val versionName: String,
        val versionCode: Long,
        val developerName: String,
        val developerMailAddress: URI,
        val developerSiteUrl: URL,
    ) : AboutUiState

    fun developerMailAddress(invoke: (URI) -> Unit): () -> Unit = {
        when (this) {
            is Loading -> Unit
            is Completed -> invoke(developerMailAddress)
        }
    }

    fun developerSiteUrl(invoke: (URL) -> Unit): () -> Unit = {
        when (this) {
            is Loading -> Unit
            is Completed -> invoke(developerSiteUrl)
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
