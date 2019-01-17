package com.kazakago.cleanarchitecture.presentation.hierarchy.about

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import com.kazakago.cleanarchitecture.domain.usecase.about.GetAppInfoUseCase
import com.kazakago.cleanarchitecture.domain.usecase.about.GetDeveloperInfoUseCase
import com.kazakago.cleanarchitecture.presentation.global.extension.toUri
import com.kazakago.cleanarchitecture.presentation.global.livedata.liveevent.NonNullLiveEvent
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.NonNullLiveData

class AboutViewModel(
    application: Application,
    getAppInfoUseCase: GetAppInfoUseCase,
    getDeveloperInfoUseCase: GetDeveloperInfoUseCase
) : AndroidViewModel(application) {

    val appInfo = NonNullLiveData(getAppInfoUseCase.execute(Unit))
    val developerInfo = NonNullLiveData(getDeveloperInfoUseCase.execute(Unit))
    val openActionView = NonNullLiveEvent<Uri>()
    val openSendTo = NonNullLiveEvent<Uri>()

    fun onClickPlayStore() {
        openActionView.call(appInfo.value.playStoreUri.toUri())
    }

    fun onClickMail() {
        openSendTo.call(developerInfo.value.getMailAddressUri().toUri())
    }

    fun onClickWebSite() {
        openActionView.call(developerInfo.value.siteUrl.toUri())
    }

}
