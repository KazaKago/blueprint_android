package com.kazakago.cleanarchitecture.presentation.extension

import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.net.URL

fun ImageView.setImageUrl(imageUrl: URL?) {
    setImageUrl(imageUrl?.toUri())
}

fun ImageView.setImageUrl(imageUri: Uri?) {
    Picasso.get().load(imageUri).into(this)
}