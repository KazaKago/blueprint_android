package com.kazakago.cleanarchitecture.presentation.global.extension

import android.content.Context
import androidx.viewbinding.ViewBinding

fun ViewBinding.context(): Context {
    return root.context
}