package com.kazakago.cleanarchitecture.presentation.extension

import android.net.Uri
import java.net.URI
import java.net.URL

fun URI.toUri(): Uri {
    return Uri.parse(toString())
}

fun URL.toUri(): Uri {
    return Uri.parse(toString())
}