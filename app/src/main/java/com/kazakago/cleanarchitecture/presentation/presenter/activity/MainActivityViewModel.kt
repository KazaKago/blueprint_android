package com.kazakago.cleanarchitecture.presentation.presenter.activity

import android.content.Context
import android.os.Bundle
import com.kazakago.cleanarchitecture.presentation.listener.presenter.activity.MainActivityViewModelListener

/**
 * Main Activity ViewModel
 *
 * @author Kensuke
 */
class MainActivityViewModel(private val context: Context) {

    var listener: MainActivityViewModelListener? = null

    fun onCreate(savedInstanceState: Bundle?) {
        listener?.initView()
        if (savedInstanceState == null) {
            listener?.replaceMainFragment()
        }
    }

    fun onClickAboutMenu() {
        listener?.toAboutActivity()
    }

}
