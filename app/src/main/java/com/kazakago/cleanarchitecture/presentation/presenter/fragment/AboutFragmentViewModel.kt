package com.kazakago.cleanarchitecture.presentation.presenter.fragment

import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.kazakago.cleanarchitecture.CleanApplication
import com.kazakago.cleanarchitecture.R
import com.kazakago.cleanarchitecture.domain.usecase.AboutUseCase
import com.kazakago.cleanarchitecture.presentation.listener.presenter.fragment.AboutFragmentViewModelListener
import javax.inject.Inject

/**
 * About Fragment ViewModel
 *
 * @author Kensuke
 */
class AboutFragmentViewModel(private val context: Context) {

    val verText = ObservableField<String>()
    val developByText = ObservableField<String>()
    val copyrightText = ObservableField<String>()

    var listener: AboutFragmentViewModelListener? = null

    @Inject
    lateinit var aboutUseCase: AboutUseCase

    init {
        CleanApplication.applicationComponent.inject(this)
    }

    fun onCreate(savedInstanceState: Bundle?){
        verText.set(context.getString(R.string.about_ver, aboutUseCase.currentVersion))
        developByText.set(context.getString(R.string.about_develop_by, context.getString(R.string.developer_name)))
        copyrightText.set(context.getString(R.string.about_copyright, aboutUseCase.currentYear, context.getString(R.string.developer_name)))
    }

    fun onClickPlayStore(view: View) {
        toPlayStore()
    }

    fun onClickMail(view: View) {
        toMailApp()
    }

    fun onClickWebSite(view: View) {
        toWebSite()
    }

    private fun toPlayStore() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(aboutUseCase.playStoreUrl))
        listener?.startActivity(intent)
    }

    private fun toMailApp() {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + aboutUseCase.mailUrl))
        listener?.startActivity(intent)
    }

    private fun toWebSite() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(aboutUseCase.webSiteUrl))
        listener?.startActivity(intent)
    }

}
