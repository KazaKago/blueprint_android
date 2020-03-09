package com.kazakago.cleanarchitecture.presentation.viewmodel.hierarchy.about

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.cleanarchitecture.domain.model.hierarchy.about.AppInfo
import com.kazakago.cleanarchitecture.domain.model.hierarchy.about.DeveloperInfo
import com.kazakago.cleanarchitecture.domain.model.global.state.State
import com.kazakago.cleanarchitecture.domain.model.global.state.StateContent
import com.kazakago.cleanarchitecture.domain.usecase.output.about.AboutOutput
import com.kazakago.cleanarchitecture.domain.usecase.hierarchy.about.SubscribeAboutUseCase
import com.kazakago.cleanarchitecture.presentation.viewmodel.global.livedata.liveevent.LiveEvent
import com.kazakago.cleanarchitecture.presentation.viewmodel.global.livedata.liveevent.MutableLiveEvent
import com.kazakago.cleanarchitecture.presentation.viewmodel.global.livedata.nullsafelivedata.MutableNullSafeLiveData
import com.kazakago.cleanarchitecture.presentation.viewmodel.global.livedata.nullsafelivedata.NullSafeLiveData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AboutViewModel(
    application: Application,
    private val subscribeAboutUseCase: SubscribeAboutUseCase
) : AndroidViewModel(application) {

    private val _appInfo = MutableNullSafeLiveData<AppInfo>()
    val appInfo: NullSafeLiveData<AppInfo> get() = _appInfo
    private val _developerInfo = MutableNullSafeLiveData<DeveloperInfo>()
    val developerInfo: NullSafeLiveData<DeveloperInfo> get() = _developerInfo
    private val _isLoading = MutableNullSafeLiveData(false)
    val isLoading: NullSafeLiveData<Boolean> get() = _isLoading
    private val _showError = MutableLiveEvent<Exception>()
    val showError: LiveEvent<Exception> get() = _showError

    init {
        subscribeAbout()
    }

    private fun subscribeAbout() = viewModelScope.launch {
        subscribeAboutUseCase().collect {
            updateAboutState(it)
            updateAboutContent(it.content)
        }
    }

    private fun updateAboutState(state: State<AboutOutput>) {
        when (state) {
            is State.Fixed -> {
                _isLoading.value = false
            }
            is State.Loading -> {
                _isLoading.value = true
            }
            is State.Error -> {
                _isLoading.value = false
                _showError.call(state.exception)
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
