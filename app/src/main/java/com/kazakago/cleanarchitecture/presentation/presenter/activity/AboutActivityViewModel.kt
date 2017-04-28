package com.kazakago.cleanarchitecture.presentation.presenter.activity

import android.content.Context
import android.os.Bundle

import com.kazakago.cleanarchitecture.presentation.listener.presenter.activity.AboutActivityViewModelListener

/**
 * About Activity ViewModel
 *
 * @author Kensuke
 */
class AboutActivityViewModel(private val context: Context) {

    var listener: AboutActivityViewModelListener? = null

    fun onCreate(savedInstanceState: Bundle?) {
        listener?.initView()
        if (savedInstanceState == null) {
            listener?.replaceAboutFragment()
        }
    }

    fun onClickBackIcon() {
        listener?.onPerformFinish()
    }

}
