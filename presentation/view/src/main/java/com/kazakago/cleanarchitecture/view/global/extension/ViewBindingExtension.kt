package com.kazakago.cleanarchitecture.view.global.extension

import android.content.Context
import androidx.viewbinding.ViewBinding

fun ViewBinding.context(): Context {
    return root.context
}