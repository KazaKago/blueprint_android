package com.kazakago.blueprint.presentation.viewmodel.hierarchy.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.blueprint.domain.usecase.hierarchy.github.GetGithubOrgsFlowUseCase
import com.kazakago.blueprint.domain.usecase.hierarchy.github.RefreshGithubOrgsUseCase
import com.kazakago.blueprint.domain.usecase.hierarchy.github.RequestAdditionalGithubOrgsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubOrgsViewModel @Inject constructor(
    private val getGithubOrgsFlowUseCase: GetGithubOrgsFlowUseCase,
    private val refreshGithubOrgsUseCase: RefreshGithubOrgsUseCase,
    private val requestAdditionalGithubOrgsUseCase: RequestAdditionalGithubOrgsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<GithubOrgsUiState>(GithubOrgsUiState.Loading)
    val uiState = _uiState.asStateFlow()
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        viewModelScope.launch { followOrgs() }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            refreshGithubOrgsUseCase()
            _isRefreshing.value = false
        }
    }

    fun retry() {
        viewModelScope.launch {
            refreshGithubOrgsUseCase()
        }
    }

    fun requestAddition() {
        viewModelScope.launch {
            requestAdditionalGithubOrgsUseCase(false)
        }
    }

    fun retryAddition() {
        viewModelScope.launch {
            requestAdditionalGithubOrgsUseCase(true)
        }
    }

    private suspend fun followOrgs() {
        getGithubOrgsFlowUseCase().collect {
            _uiState.value = it.doAction(
                onLoading = { githubOrgs ->
                    if (githubOrgs != null) {
                        GithubOrgsUiState.Completed(
                            githubOrgs = githubOrgs,
                        )
                    } else {
                        GithubOrgsUiState.Loading
                    }
                },
                onCompleted = { githubOrgs, next, _ ->
                    next.doAction(
                        onFixed = {
                            GithubOrgsUiState.Completed(
                                githubOrgs = githubOrgs,
                            )
                        },
                        onLoading = {
                            GithubOrgsUiState.AdditionalLoading(
                                githubOrgs = githubOrgs,
                            )
                        },
                        onError = { exception ->
                            GithubOrgsUiState.AdditionalError(
                                githubOrgs = githubOrgs,
                                error = exception,
                            )
                        },
                    )
                },
                onError = { exception ->
                    GithubOrgsUiState.Error(
                        error = exception,
                    )
                }
            )
        }
    }
}
