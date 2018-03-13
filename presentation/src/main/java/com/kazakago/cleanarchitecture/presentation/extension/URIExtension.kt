package com.kazakago.cleanarchitecture.presentation.extension

import android.net.Uri
import java.net.URI

fun URI.toUri(): Uri {
    return Uri.parse(toString())
}
