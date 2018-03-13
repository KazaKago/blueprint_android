package com.kazakago.cleanarchitecture.presentation.listener.fragment

import android.net.Uri

interface AboutFragmentViewModelListener {

    fun openActionView(uri: Uri)

    fun openSendTo(uri: Uri)

    fun showToast(message: String?)

}
