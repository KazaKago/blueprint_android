package com.kazakago.cleanarchitecture.presentation.utils

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView

import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun ImageView.imageUrl(url: String) {
    Picasso.with(this.context).load(url).into(this)
}

@BindingAdapter("imageUrl", "error")
fun ImageView.imageUrl(url: String, error: Drawable) {
    Picasso.with(this.context).load(url).error(error).into(this)
}