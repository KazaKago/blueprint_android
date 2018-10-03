package com.kazakago.cleanarchitecture.presentation.extension

import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.net.URL

fun ImageView.loadImageUrl(imageUrl: URL?) {
    loadImageUrl(imageUrl?.toUri())
}

fun ImageView.loadImageUrl(imageUri: Uri?) {
    Picasso.get().load(imageUri).into(this)
}