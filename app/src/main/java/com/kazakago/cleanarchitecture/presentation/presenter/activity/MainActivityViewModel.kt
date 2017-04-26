package com.kazakago.cleanarchitecture.presentation.presenter.activity

import android.content.Context
import com.kazakago.cleanarchitecture.presentation.listener.presenter.activity.MainActivityViewModelListener

/**
 * Main Activity ViewModel
 *
 * @author Kensuke
 */
class MainActivityViewModel(private val context: Context) {

    var listener: MainActivityViewModelListener? = null

    fun onClickAboutMenu() {
        listener?.toAboutActivity()
    }

}
