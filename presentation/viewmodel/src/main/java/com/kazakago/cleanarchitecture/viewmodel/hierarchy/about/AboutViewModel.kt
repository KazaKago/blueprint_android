package com.kazakago.cleanarchitecture.viewmodel.hierarchy.about

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.cleanarchitecture.model.about.AppInfo
import com.kazakago.cleanarchitecture.model.about.DeveloperInfo
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.state.StoreValue
import com.kazakago.cleanarchitecture.usecase.output.about.AboutOutput
import com.kazakago.cleanarchitecture.usecase.usecase.about.SubscribeAboutUseCase
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.liveevent.LiveEvent
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.liveevent.MutableLiveEvent
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata.MutableNullSafeLiveData
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata.NullSafeLiveData
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
            updateAboutValue(it.value)
        }
    }

    private fun updateAboutState(state: StoreState<AboutOutput>) {
        when (state) {
            is StoreState.Fixed -> {
                _isLoading.value = false
            }
            is StoreState.Loading -> {
                _isLoading.value = true
            }
            is StoreState.Error -> {
                _isLoading.value = false
                _showError.call(state.exception)
            }
        }
    }

    private fun updateAboutValue(value: StoreValue<AboutOutput>) {
        when (value) {
            is StoreValue.Stored -> {
                _appInfo.value = value.value.appInfo
                _developerInfo.value = value.value.developerInfo
            }
            is StoreValue.NotStored -> {
                //do nothing.
            }
        }
    }

}
