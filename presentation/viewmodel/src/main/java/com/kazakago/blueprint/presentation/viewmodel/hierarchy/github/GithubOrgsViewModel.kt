package com.kazakago.blueprint.presentation.viewmodel.hierarchy.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.blueprint.domain.usecase.hierarchy.github.FollowGithubOrgsUseCase
import com.kazakago.blueprint.domain.usecase.hierarchy.github.RefreshGithubOrgsUseCase
import com.kazakago.blueprint.domain.usecase.hierarchy.github.RequestAdditionalGithubOrgsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubOrgsViewModel @Inject constructor(
    private val followGithubOrgsUseCase: FollowGithubOrgsUseCase,
    private val refreshGithubOrgsUseCase: RefreshGithubOrgsUseCase,
    private val requestAdditionalGithubOrgsUseCase: RequestAdditionalGithubOrgsUseCase,
) : ViewModel() {

    private val _githubOrgs = MutableStateFlow<List<GithubOrg>>(emptyList())
    val githubOrgs = _githubOrgs.asStateFlow()
    private val _isMainLoading = MutableStateFlow(false)
    val isMainLoading = _isMainLoading.asStateFlow()
    private val _isAdditionalLoading = MutableStateFlow(false)
    val isAdditionalLoading = _isAdditionalLoading.asStateFlow()
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()
    private val _mainError = MutableStateFlow<Exception?>(null)
    val mainError = _mainError.asStateFlow()
    private val _additionalError = MutableStateFlow<Exception?>(null)
    val additionalError = _additionalError.asStateFlow()

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
        followGithubOrgsUseCase().collect {
            it.doAction(
                onLoading = { githubOrgs ->
                    if (githubOrgs != null) {
                        _githubOrgs.value = githubOrgs
                        _isMainLoading.value = false
                    } else {
                        _githubOrgs.value = emptyList()
                        _isMainLoading.value = true
                    }
                    _isAdditionalLoading.value = false
                    _mainError.value = null
                    _additionalError.value = null
                },
                onCompleted = { githubOrgs, next, _ ->
                    next.doAction(
                        onFixed = {
                            _isAdditionalLoading.value = false
                            _additionalError.value = null
                        },
                        onLoading = {
                            _isAdditionalLoading.value = true
                            _additionalError.value = null
                        },
                        onError = { exception ->
                            _isAdditionalLoading.value = false
                            _additionalError.value = exception
                        },
                    )
                    _githubOrgs.value = githubOrgs
                    _isMainLoading.value = false
                    _mainError.value = null
                },
                onError = { exception ->
                    _githubOrgs.value = emptyList()
                    _isMainLoading.value = false
                    _isAdditionalLoading.value = false
                    _mainError.value = exception
                    _additionalError.value = null
                }
            )
        }
    }
}
