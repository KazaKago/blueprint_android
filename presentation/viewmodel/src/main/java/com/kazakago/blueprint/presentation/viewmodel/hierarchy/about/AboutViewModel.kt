package com.kazakago.blueprint.presentation.viewmodel.hierarchy.about

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.blueprint.domain.model.hierarchy.about.AppInfo
import com.kazakago.blueprint.domain.model.hierarchy.about.DeveloperInfo
import com.kazakago.blueprint.domain.usecase.hierarchy.about.GetAboutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    application: Application,
    private val getAboutUseCase: GetAboutUseCase,
) : AndroidViewModel(application) {

    private val _appInfo = MutableStateFlow<AppInfo?>(null)
    val appInfo get() = _appInfo.asStateFlow()
    private val _developerInfo = MutableStateFlow<DeveloperInfo?>(null)
    val developerInfo get() = _developerInfo.asStateFlow()

    init {
        viewModelScope.launch { loadAboutInfo() }
    }

    private suspend fun loadAboutInfo() {
        val aboutInfo = getAboutUseCase()
        _appInfo.emit(aboutInfo.appInfo)
        _developerInfo.emit(aboutInfo.developerInfo)
    }
}
