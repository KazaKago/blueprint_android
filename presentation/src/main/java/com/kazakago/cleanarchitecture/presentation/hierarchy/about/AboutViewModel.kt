package com.kazakago.cleanarchitecture.presentation.hierarchy.about

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import com.kazakago.cleanarchitecture.domain.usecase.about.GetAppInfoUseCase
import com.kazakago.cleanarchitecture.domain.usecase.about.GetDeveloperInfoUseCase
import com.kazakago.cleanarchitecture.presentation.global.extension.toUri
import com.kazakago.cleanarchitecture.presentation.global.livedata.liveevent.NonNullLiveEvent
import com.kazakago.cleanarchitecture.presentation.global.livedata.liveevent.NonNullMutableLiveEvent
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.NonNullLiveData

class AboutViewModel(
    application: Application,
    getAppInfoUseCase: GetAppInfoUseCase,
    getDeveloperInfoUseCase: GetDeveloperInfoUseCase
) : AndroidViewModel(application) {

    val appInfo = NonNullLiveData(getAppInfoUseCase(Unit))
    val developerInfo = NonNullLiveData(getDeveloperInfoUseCase(Unit))
    private val _openActionView = NonNullMutableLiveEvent<Uri>()
    val openActionView: NonNullLiveEvent<Uri> get() = _openActionView
    private val _openSendTo = NonNullMutableLiveEvent<Uri>()
    val openSendTo: NonNullLiveEvent<Uri> get() = _openSendTo

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
