package com.kazakago.blueprint.presentation.viewmodel.hierarchy.github

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubRepo

@Stable
sealed interface GithubReposUiState {

    val githubOrgName: GithubOrgName

    data class Loading(
        override val githubOrgName: GithubOrgName,
    ) : GithubReposUiState

    data class Completed(
        val githubOrg: GithubOrg,
        val githubRepos: List<GithubRepo>,
    ) : GithubReposUiState {
        override val githubOrgName = githubOrg.name
    }

    data class Error(
        override val githubOrgName: GithubOrgName,
        val error: Exception,
    ) : GithubReposUiState

    data class AdditionalLoading(
        val githubOrg: GithubOrg,
        val githubRepos: List<GithubRepo>,
    ) : GithubReposUiState {
        override val githubOrgName = githubOrg.name
    }

    data class AdditionalError(
        val githubOrg: GithubOrg,
        val githubRepos: List<GithubRepo>,
        val error: Exception,
    ) : GithubReposUiState {
        override val githubOrgName = githubOrg.name
    }

    fun githubReposOrEmpty() = when (this) {
        is AdditionalError -> githubRepos
        is AdditionalLoading -> githubRepos
        is Completed -> githubRepos
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
