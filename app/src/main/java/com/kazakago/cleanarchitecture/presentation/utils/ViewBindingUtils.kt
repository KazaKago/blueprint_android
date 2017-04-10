package com.kazakago.cleanarchitecture.presentation.utils

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView

import com.squareup.picasso.Picasso

object ViewBindingUtils {

    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String) {
        Picasso.with(view.context).load(url).into(view)
    }

    @BindingAdapter("imageUrl", "error")
    fun loadImage(view: ImageView, url: String, error: Drawable) {
        Picasso.with(view.context).load(url).error(error).into(view)
    }

}
