package com.kazakago.cleanarchitecture.presentation.view.global.extension

import android.content.Context
import androidx.viewbinding.ViewBinding

internal fun ViewBinding.context(): Context {
    return root.context
}