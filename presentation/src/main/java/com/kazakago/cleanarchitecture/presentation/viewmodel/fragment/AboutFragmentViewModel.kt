package com.kazakago.cleanarchitecture.presentation.viewmodel.fragment

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.LazyKodeinAware
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.kazakago.cleanarchitecture.domain.model.appinfo.AppInfo
import com.kazakago.cleanarchitecture.domain.usecase.appinfo.GetAppInfoUseCase
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.extension.compositeLocalizedMessage
import com.kazakago.cleanarchitecture.presentation.extension.toUri
import com.kazakago.cleanarchitecture.presentation.listener.fragment.AboutFragmentViewModelListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
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
    private var appInfo: AppInfo? = null

    init {
        getAppInfo()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun getAppInfo() {
        compositeDisposable.add(getAppInfoUseCase.execute(Unit)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            appInfo = it
                            developByText.value = getApplication<Application>().getString(R.string.about_develop_by, it.developerName)
                            versionText.value = getApplication<Application>().getString(R.string.about_ver, it.versionName)
                            copyrightText.value = getApplication<Application>().getString(R.string.about_copyright, Calendar.getInstance().get(Calendar.YEAR), it.developerName)
                        },
                        onError = {
                            listener?.showToast(it.compositeLocalizedMessage())
                        }
                ))
    }

    fun onClickPlayStore() {
        appInfo?.playStore?.toUri()?.let {
            listener?.openActionView(it)
        }
    }

    fun onClickMail() {
        appInfo?.getEmailUri()?.toUri()?.let {
            listener?.openSendTo(it)
        }
    }

    fun onClickWebSite() {
        appInfo?.officialSite?.toUri()?.let {
            listener?.openActionView(it)
        }
    }

}
