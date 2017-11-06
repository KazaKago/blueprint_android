package com.kazakago.cleanarchitecture.presentation.presenter.fragment

import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.LazyKodeinAware
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.kazakago.cleanarchitecture.R
import com.kazakago.cleanarchitecture.domain.usecase.appInfo.GetAppVersionUseCase
import com.kazakago.cleanarchitecture.domain.usecase.appInfo.GetMailAddressUrlUseCase
import com.kazakago.cleanarchitecture.domain.usecase.appInfo.GetOfficialSiteUrlUseCase
import com.kazakago.cleanarchitecture.domain.usecase.appInfo.GetPlayStoreUrlUseCase
import com.kazakago.cleanarchitecture.presentation.listener.presenter.fragment.AboutFragmentViewModelListener
import java.util.*

class AboutFragmentViewModel(private val context: Context, private val listener: AboutFragmentViewModelListener): LazyKodeinAware {

    override val kodein = LazyKodein(context.appKodein)

    val verText = ObservableField<String>()
    val developByText = ObservableField<String>()
    val copyrightText = ObservableField<String>()

    private val getAppVersionUseCase: GetAppVersionUseCase by instance()
    private val getPlayStoreUrlUseCase: GetPlayStoreUrlUseCase by instance()
    private val getMailAddressUrlUseCase: GetMailAddressUrlUseCase by instance()
    private val getOfficialSiteUrlUseCase: GetOfficialSiteUrlUseCase by instance()

    fun onViewCreated(savedInstanceState: Bundle?){
        verText.set(context.getString(R.string.about_ver, getAppVersionUseCase.execute(Unit)))
        developByText.set(context.getString(R.string.about_develop_by, context.getString(R.string.developer_name)))
        copyrightText.set(context.getString(R.string.about_copyright, Calendar.getInstance().get(Calendar.YEAR), context.getString(R.string.developer_name)))
    }

    fun onClickPlayStore(view: View?) {
        toPlayStore()
    }

    fun onClickMail(view: View?) {
        toMailApp()
    }

    fun onClickWebSite(view: View?) {
        toWebSite()
    }

    private fun toPlayStore() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getPlayStoreUrlUseCase.execute(Unit)))
        listener.startActivity(intent = intent)
    }

    private fun toMailApp() {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + getMailAddressUrlUseCase.execute(Unit)))
        listener.startActivity(intent = intent)
    }

    private fun toWebSite() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getOfficialSiteUrlUseCase.execute(Unit)))
        listener.startActivity(intent = intent)
    }

}
