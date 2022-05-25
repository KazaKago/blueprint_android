package com.kazakago.blueprint.presentation.controller.hierarchy.github

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.blueprint.domain.usecase.hierarchy.github.GetGithubReposFlowUseCase
import com.kazakago.blueprint.domain.usecase.hierarchy.github.RefreshGithubReposUseCase
import com.kazakago.blueprint.domain.usecase.hierarchy.github.RequestAdditionalGithubReposUseCase
import com.kazakago.blueprint.presentation.controller.hierarchy.destinations.GithubReposControllerDestination
import com.kazakago.blueprint.presentation.controller.hierarchy.navArgs
import com.kazakago.blueprint.presentation.uistate.hierarchy.github.GithubReposUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubReposViewModel @Inject constructor(
    private val getGithubReposFlowUseCase: GetGithubReposFlowUseCase,
    private val refreshGithubReposUseCase: RefreshGithubReposUseCase,
    private val requestAdditionalGithubReposUseCase: RequestAdditionalGithubReposUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = savedStateHandle.navArgs<GithubReposControllerDestination.NavArgs>()
    private val _uiState = MutableStateFlow<GithubReposUiState>(GithubReposUiState.Loading(args.name))
    val uiState = _uiState.asStateFlow()
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        viewModelScope.launch { followGithubRepos() }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            refreshGithubReposUseCase(args.name)
            _isRefreshing.value = false
        }
    }

    fun retry() {
        viewModelScope.launch {
            refreshGithubReposUseCase(args.name)
        }
    }

    fun requestAddition() {
        viewModelScope.launch {
            requestAdditionalGithubReposUseCase(args.name, continueWhenError = false)
        }
    }

    fun retryAddition() {
        viewModelScope.launch {
            requestAdditionalGithubReposUseCase(args.name, continueWhenError = true)
        }
    }

    private suspend fun followGithubRepos() {
        getGithubReposFlowUseCase(args.name).collect {
            _uiState.value = it.doAction(
                onLoading = { githubOrgAndRepos ->
                    if (githubOrgAndRepos != null) {
                        GithubReposUiState.Completed(
                            githubOrg = githubOrgAndRepos.githubOrg,
                            githubRepos = githubOrgAndRepos.githubRepos,
                        )
                    } else {
                        GithubReposUiState.Loading(
                            githubOrgName = args.name,
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
                        githubOrgName = args.name,
                        error = exception,
                    )
                }
            )
        }
    }
}
