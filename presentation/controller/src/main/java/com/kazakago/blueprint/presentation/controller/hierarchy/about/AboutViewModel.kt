package com.kazakago.blueprint.presentation.controller.hierarchy.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.blueprint.domain.usecase.hierarchy.about.GetAboutInfoUseCase
import com.kazakago.blueprint.presentation.uistate.hierarchy.about.AboutUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val getAboutInfoUseCase: GetAboutInfoUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<AboutUiState>(AboutUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch { loadAboutInfo() }
    }

    private suspend fun loadAboutInfo() {
        val aboutInfo = getAboutInfoUseCase()
        _uiState.emit(
            AboutUiState.Completed(
                appInfo = aboutInfo.appInfo,
                developerInfo = aboutInfo.developerInfo,
            )
        )
    }
}
