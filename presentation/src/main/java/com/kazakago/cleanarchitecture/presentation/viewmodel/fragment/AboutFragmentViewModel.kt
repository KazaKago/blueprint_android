package com.kazakago.cleanarchitecture.presentation.viewmodel.fragment

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.net.Uri
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.LazyKodeinAware
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.kazakago.cleanarchitecture.domain.usecase.appInfo.GetAppVersionUseCase
import com.kazakago.cleanarchitecture.domain.usecase.appInfo.GetMailAddressUrlUseCase
import com.kazakago.cleanarchitecture.domain.usecase.appInfo.GetOfficialSiteUrlUseCase
import com.kazakago.cleanarchitecture.domain.usecase.appInfo.GetPlayStoreUrlUseCase
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.listener.fragment.AboutFragmentViewModelListener
import java.util.*

class AboutFragmentViewModel(application: Application) : AndroidViewModel(application), LazyKodeinAware {

    class Factory(private val application: Application): ViewModelProvider.Factory {
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
    private val getAppVersionUseCase: GetAppVersionUseCase by instance()
    private val getPlayStoreUrlUseCase: GetPlayStoreUrlUseCase by instance()
    private val getMailAddressUrlUseCase: GetMailAddressUrlUseCase by instance()
    private val getOfficialSiteUrlUseCase: GetOfficialSiteUrlUseCase by instance()

    init {
        versionText.value = getApplication<Application>().getString(R.string.about_ver, getAppVersionUseCase.execute(Unit))
        developByText.value = getApplication<Application>().getString(R.string.about_develop_by, getApplication<Application>().getString(R.string.developer_name))
        copyrightText.value = getApplication<Application>().getString(R.string.about_copyright, Calendar.getInstance().get(Calendar.YEAR), getApplication<Application>().getString(R.string.developer_name))
    }

    fun onClickPlayStore() {
        listener?.openActionView(Uri.parse(getPlayStoreUrlUseCase.execute(Unit)))
    }

    fun onClickMail() {
        listener?.openSendTo(Uri.parse("mailto:" + getMailAddressUrlUseCase.execute(Unit)))
    }

    fun onClickWebSite() {
        listener?.openActionView(Uri.parse(getOfficialSiteUrlUseCase.execute(Unit)))
    }

}
