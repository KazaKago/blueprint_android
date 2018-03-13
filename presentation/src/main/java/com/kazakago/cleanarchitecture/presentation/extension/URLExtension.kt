package com.kazakago.cleanarchitecture.presentation.extension

import android.net.Uri
import java.net.URL

fun URL.toUri(): Uri {
    return Uri.parse(toString())
}