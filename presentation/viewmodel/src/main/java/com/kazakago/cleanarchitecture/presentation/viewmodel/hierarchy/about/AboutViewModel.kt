package com.kazakago.cleanarchitecture.presentation.viewmodel.hierarchy.about

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.cleanarchitecture.domain.model.global.state.State
import com.kazakago.cleanarchitecture.domain.model.global.state.StateContent
import com.kazakago.cleanarchitecture.domain.model.hierarchy.about.AppInfo
import com.kazakago.cleanarchitecture.domain.model.hierarchy.about.DeveloperInfo
import com.kazakago.cleanarchitecture.domain.usecase.hierarchy.about.SubscribeAboutUseCase
import com.kazakago.cleanarchitecture.domain.usecase.output.about.AboutOutput
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AboutViewModel(
    application: Application,
    private val subscribeAboutUseCase: SubscribeAboutUseCase
) : AndroidViewModel(application) {

    private val _appInfo = MutableStateFlow<AppInfo?>(null)
    val appInfo: StateFlow<AppInfo?> get() = _appInfo
    private val _developerInfo = MutableStateFlow<DeveloperInfo?>(null)
    val developerInfo: StateFlow<DeveloperInfo?> get() = _developerInfo
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading
    private val _showError = MutableSharedFlow<Exception>()
    val showError: SharedFlow<Exception> get() = _showError

    init {
        subscribeAbout()
    }

    private fun subscribeAbout() = viewModelScope.launch {
        subscribeAboutUseCase().collect {
            updateAboutState(it)
            updateAboutContent(it.content)
        }
    }

    private suspend fun updateAboutState(state: State<AboutOutput>) {
        when (state) {
            is State.Fixed -> {
                _isLoading.value = false
            }
            is State.Loading -> {
                _isLoading.value = true
            }
            is State.Error -> {
                _isLoading.value = false
                _showError.emit(state.exception)
            }
        }
    }

    private fun updateAboutContent(content: StateContent<AboutOutput>) {
        when (content) {
            is StateContent.Exist -> {
                _appInfo.value = content.rawContent.appInfo
                _developerInfo.value = content.rawContent.developerInfo
            }
            is StateContent.NotExist -> {
                //do nothing.
            }
        }
    }

}
