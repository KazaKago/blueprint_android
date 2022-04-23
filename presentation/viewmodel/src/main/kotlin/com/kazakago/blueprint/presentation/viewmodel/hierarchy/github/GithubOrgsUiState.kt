package com.kazakago.blueprint.presentation.viewmodel.hierarchy.github

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg

@Stable
sealed interface GithubOrgsUiState {

    object Loading : GithubOrgsUiState

    data class Completed(
        val githubOrgs: List<GithubOrg>,
    ) : GithubOrgsUiState

    data class Error(
        val error: Exception,
    ) : GithubOrgsUiState

    data class AdditionalLoading(
        val githubOrgs: List<GithubOrg>,
    ) : GithubOrgsUiState

    data class AdditionalError(
        val githubOrgs: List<GithubOrg>,
        val error: Exception,
    ) : GithubOrgsUiState

    fun getGithubOrgsOrEmpty() = when (this) {
        is AdditionalError -> githubOrgs
        is AdditionalLoading -> githubOrgs
        is Completed -> githubOrgs
        is Error -> emptyList()
        is Loading -> emptyList()
    }

    @Composable
    fun MayLayout(
        onLoading: @Composable (uiState: Loading) -> Unit = {},
        onCompleted: @Composable (uiState: Completed) -> Unit = {},
        onError: @Composable (uiState: Error) -> Unit = {},
        onAdditionalError: @Composable (uiState: AdditionalError) -> Unit = {},
        onAdditionalLoading: @Composable (uiState: AdditionalLoading) -> Unit = {},
    ) {
        when (this) {
            is Loading -> onLoading(this)
            is Completed -> onCompleted(this)
            is Error -> onError(this)
            is AdditionalError -> onAdditionalError(this)
            is AdditionalLoading -> onAdditionalLoading(this)
        }
    }
}
