package com.kazakago.cleanarchitecture.presentation.hierarchy.about

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import com.kazakago.cleanarchitecture.domain.model.about.AppInfo
import com.kazakago.cleanarchitecture.domain.model.about.DeveloperInfo
import com.kazakago.cleanarchitecture.domain.usecase.about.GetAppInfoUseCase
import com.kazakago.cleanarchitecture.domain.usecase.about.GetDeveloperInfoUseCase
import com.kazakago.cleanarchitecture.presentation.global.extension.toUri
import com.kazakago.cleanarchitecture.presentation.global.livedata.liveevent.LiveEvent
import com.kazakago.cleanarchitecture.presentation.global.livedata.liveevent.MutableLiveEvent
import com.kazakago.cleanarchitecture.presentation.global.livedata.nullsafelivedata.NullSafeLiveData

class AboutViewModel(
    application: Application,
    getAppInfoUseCase: GetAppInfoUseCase,
    getDeveloperInfoUseCase: GetDeveloperInfoUseCase
) : AndroidViewModel(application) {

    private val _appInfo = NullSafeLiveData(getAppInfoUseCase())
    val appInfo: NullSafeLiveData<AppInfo> get() = _appInfo
    private val _developerInfo = NullSafeLiveData(getDeveloperInfoUseCase())
    val developerInfo: NullSafeLiveData<DeveloperInfo> get() = _developerInfo
    private val _openActionView = MutableLiveEvent<Uri>()
    val openActionView: LiveEvent<Uri> get() = _openActionView
    private val _openSendTo = MutableLiveEvent<Uri>()
    val openSendTo: LiveEvent<Uri> get() = _openSendTo

    fun onClickPlayStore() {
        _openActionView.call(appInfo.value.playStoreUri.toUri())
    }

    fun onClickMail() {
        _openSendTo.call(developerInfo.value.getMailAddressUri().toUri())
    }

    fun onClickWebSite() {
        _openActionView.call(developerInfo.value.siteUrl.toUri())
    }

}
