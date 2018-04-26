package com.kazakago.cleanarchitecture.presentation.hierarchy.about

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.net.Uri
import com.kazakago.cleanarchitecture.domain.model.about.AppInfo
import com.kazakago.cleanarchitecture.domain.model.about.DeveloperInfo
import com.kazakago.cleanarchitecture.domain.usecase.about.GetAppInfoUseCase
import com.kazakago.cleanarchitecture.domain.usecase.about.GetDeveloperInfoUseCase
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.extension.compositeLocalizedMessage
import com.kazakago.cleanarchitecture.presentation.extension.toUri
import com.kazakago.cleanarchitecture.presentation.livedata.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Singles
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.*

class AboutFragmentViewModel(application: Application,
                             private val getAppInfoUseCase: GetAppInfoUseCase,
                             private val getDeveloperInfoUseCase: GetDeveloperInfoUseCase ) : AndroidViewModel(application) {

    val versionText = MutableLiveData<String>()
    val developByText = MutableLiveData<String>()
    val copyrightText = MutableLiveData<String>()
    val openActionView = SingleLiveEvent<Uri>()
    val openSendTo = SingleLiveEvent<Uri>()
    val showToast = SingleLiveEvent<String>()

    private val compositeDisposable = CompositeDisposable()
    private var appInfo: AppInfo? = null
    private var developerInfo: DeveloperInfo? = null

    init {
        getAboutInfo()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun getAboutInfo() {
        compositeDisposable.add(Singles.zip(getAppInfoUseCase.execute(Unit), getDeveloperInfoUseCase.execute(Unit))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            appInfo = it.first
                            developerInfo = it.second
                            versionText.value = getApplication<Application>().getString(R.string.about_ver, it.first.versionName)
                            copyrightText.value = getApplication<Application>().getString(R.string.about_copyright, Calendar.getInstance().get(Calendar.YEAR), it.second.name)
                        },
                        onError = {
                            showToast.call(it.compositeLocalizedMessage())
                        }
                ))
    }

    fun onClickPlayStore() {
        openActionView.call(appInfo?.playStoreUri?.toUri())
    }

    fun onClickMail() {
        openSendTo.call(developerInfo?.getMailAddressUri()?.toUri())
    }

    fun onClickWebSite() {
        openActionView.call(developerInfo?.siteUrl?.toUri())
    }

}
