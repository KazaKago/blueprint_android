package com.kazakago.blueprint.presentation.viewmodel.hierarchy.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.domain.usecase.hierarchy.github.GetGithubReposFlowUseCase
import com.kazakago.blueprint.domain.usecase.hierarchy.github.RefreshGithubReposUseCase
import com.kazakago.blueprint.domain.usecase.hierarchy.github.RequestAdditionalGithubReposUseCase
import com.kazakago.blueprint.presentation.uistate.hierarchy.github.GithubReposUiState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GithubReposViewModel @AssistedInject constructor(
    private val getGithubReposFlowUseCase: GetGithubReposFlowUseCase,
    private val refreshGithubReposUseCase: RefreshGithubReposUseCase,
    private val requestAdditionalGithubReposUseCase: RequestAdditionalGithubReposUseCase,
    @Assisted private val githubOrgName: GithubOrgName,
) : ViewModel() {

    companion object {
        fun provideFactory(assistedFactory: Factory, githubOrgName: GithubOrgName): ViewModelProvider.Factory = object : ViewModelProvider.NewInstanceFactory() {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(githubOrgName) as T
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(githubOrgName: GithubOrgName): GithubReposViewModel
    }

    private val _uiState = MutableStateFlow<GithubReposUiState>(GithubReposUiState.Loading(githubOrgName))
    val uiState = _uiState.asStateFlow()
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        viewModelScope.launch { followGithubRepos() }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            refreshGithubReposUseCase(githubOrgName)
            _isRefreshing.value = false
        }
    }

    fun retry() {
        viewModelScope.launch {
            refreshGithubReposUseCase(githubOrgName)
        }
    }

    fun requestAddition() {
        viewModelScope.launch {
            requestAdditionalGithubReposUseCase(githubOrgName, continueWhenError = false)
        }
    }

    fun retryAddition() {
        viewModelScope.launch {
            requestAdditionalGithubReposUseCase(githubOrgName, continueWhenError = true)
        }
    }

    private suspend fun followGithubRepos() {
        getGithubReposFlowUseCase(githubOrgName).collect {
            _uiState.value = it.doAction(
                onLoading = { githubOrgAndRepos ->
                    if (githubOrgAndRepos != null) {
                        GithubReposUiState.Completed(
                            githubOrg = githubOrgAndRepos.githubOrg,
                            githubRepos = githubOrgAndRepos.githubRepos,
                        )
                    } else {
                        GithubReposUiState.Loading(
                            githubOrgName = githubOrgName,
                        )
                    }
                },
                onCompleted = { githubOrgAndRepos, next, _ ->
                    next.doAction(
                        onFixed = {
                            GithubReposUiState.Completed(
                                githubOrg = githubOrgAndRepos.githubOrg,
                                githubRepos = githubOrgAndRepos.githubRepos,
                            )
                        },
                        onLoading = {
                            GithubReposUiState.AdditionalLoading(
                                githubOrg = githubOrgAndRepos.githubOrg,
                                githubRepos = githubOrgAndRepos.githubRepos,
                            )
                        },
                        onError = { exception ->
                            GithubReposUiState.AdditionalError(
                                githubOrg = githubOrgAndRepos.githubOrg,
                                githubRepos = githubOrgAndRepos.githubRepos,
                                error = exception,
                            )
                        },
                    )
                },
                onError = { exception ->
                    GithubReposUiState.Error(
                        githubOrgName = githubOrgName,
                        error = exception,
                    )
                }
            )
        }
    }
}
