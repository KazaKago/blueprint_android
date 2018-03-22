package com.kazakago.cleanarchitecture.presentation.hierarchy.about

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.LazyKodeinAware
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.kazakago.cleanarchitecture.domain.model.about.AppInfo
import com.kazakago.cleanarchitecture.domain.model.about.DeveloperInfo
import com.kazakago.cleanarchitecture.domain.usecase.about.GetAppInfoUseCase
import com.kazakago.cleanarchitecture.domain.usecase.about.GetDeveloperInfoUseCase
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.extension.compositeLocalizedMessage
import com.kazakago.cleanarchitecture.presentation.extension.toUri
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Singles
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.*

class AboutFragmentViewModel(application: Application) : AndroidViewModel(application), LazyKodeinAware {

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AboutFragmentViewModel(application) as T
        }
    }

    override val kodein = LazyKodein(application.appKodein)

    val versionText = MutableLiveData<String>()
    val developByText = MutableLiveData<String>()
    val copyrightText = MutableLiveData<String>()

    var listener: AboutFragmentViewModelListener? = null
    private val compositeDisposable = CompositeDisposable()
    private val getAppInfoUseCase: GetAppInfoUseCase by instance()
    private val getDeveloperInfoUseCase: GetDeveloperInfoUseCase by instance()
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
                            listener?.showToast(it.compositeLocalizedMessage())
                        }
                ))
    }

    fun onClickPlayStore() {
        appInfo?.playStoreUri?.toUri()?.let {
            listener?.openActionView(it)
        }
    }

    fun onClickMail() {
        developerInfo?.getMailAddressUri()?.toUri()?.let {
            listener?.openSendTo(it)
        }
    }

    fun onClickWebSite() {
        developerInfo?.siteUrl?.toUri()?.let {
            listener?.openActionView(it)
        }
    }

}
