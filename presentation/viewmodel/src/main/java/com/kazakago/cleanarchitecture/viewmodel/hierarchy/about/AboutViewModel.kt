package com.kazakago.cleanarchitecture.viewmodel.hierarchy.about

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.cleanarchitecture.model.about.AppInfo
import com.kazakago.cleanarchitecture.model.about.DeveloperInfo
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.state.StoreValue
import com.kazakago.cleanarchitecture.usecase.usecase.about.SubscribeAppInfoUseCase
import com.kazakago.cleanarchitecture.usecase.usecase.about.SubscribeDeveloperInfoUseCase
import com.kazakago.cleanarchitecture.viewmodel.global.extension.toUri
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.liveevent.LiveEvent
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.liveevent.MutableLiveEvent
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata.MutableNullSafeLiveData
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata.NullSafeLiveData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AboutViewModel(
    application: Application,
    private val subscribeAppInfoUseCase: SubscribeAppInfoUseCase,
    private val subscribeDeveloperInfoUseCase: SubscribeDeveloperInfoUseCase
) : AndroidViewModel(application) {

    private val _appInfo = MutableNullSafeLiveData<StoreState<AppInfo>>()
    val appInfo: NullSafeLiveData<StoreState<AppInfo>> get() = _appInfo
    private val _developerInfo = MutableNullSafeLiveData<StoreState<DeveloperInfo>>()
    val developerInfo: NullSafeLiveData<StoreState<DeveloperInfo>> get() = _developerInfo
    private val _openActionView = MutableLiveEvent<Uri>()
    val openActionView: LiveEvent<Uri> get() = _openActionView
    private val _openSendTo = MutableLiveEvent<Uri>()
    val openSendTo: LiveEvent<Uri> get() = _openSendTo

    init {
        subscribeAppInfo()
        subscribeDeveloperInfo()
    }

    private fun subscribeAppInfo() = viewModelScope.launch {
        subscribeAppInfoUseCase().collect {
            _appInfo.value = it
        }
    }

    private fun subscribeDeveloperInfo() = viewModelScope.launch {
        subscribeDeveloperInfoUseCase().collect {
            _developerInfo.value = it
        }
    }

    fun onClickPlayStore() {
        when (val storeValue = appInfo.value.value) {
            is StoreValue.Stored -> _openActionView.call(storeValue.value.playStoreUri.toUri())
            is StoreValue.NotStored -> Unit //do nothing
        }
    }

    fun onClickMail() {
        when (val storeValue = developerInfo.value.value) {
            is StoreValue.Stored -> _openSendTo.call(storeValue.value.mailAddress.toURI().toUri())
            is StoreValue.NotStored -> Unit //do nothing
        }
    }

    fun onClickWebSite() {
        when (val storeValue = developerInfo.value.value) {
            is StoreValue.Stored -> _openActionView.call(storeValue.value.siteUrl.toUri())
            is StoreValue.NotStored -> Unit //do nothing
        }
    }

}
