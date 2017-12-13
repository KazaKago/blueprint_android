package com.kazakago.cleanarchitecture.presentation.listener.presenter.fragment

import android.net.Uri

interface AboutFragmentViewModelListener {

    fun openActionView(uri: Uri)

    fun openSendTo(uri: Uri)

}
