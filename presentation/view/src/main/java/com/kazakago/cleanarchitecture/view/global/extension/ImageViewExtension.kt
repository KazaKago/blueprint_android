package com.kazakago.cleanarchitecture.view.global.extension

import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.kazakago.cleanarchitecture.viewmodel.global.extension.toUri
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import java.io.File
import java.net.URL

internal fun ImageView.loadImageUrl(imageUrl: URL?, onSuccess: (() -> Unit)? = null, onError: (() -> Unit)? = null) {
    loadImageUri(imageUrl?.toUri(), onSuccess, onError)
}

internal fun ImageView.loadImageUri(imageUri: Uri?, onSuccess: (() -> Unit)? = null, onError: (() -> Unit)? = null) {
    load(Picasso.get().load(imageUri), onSuccess, onError)
}

internal fun ImageView.loadImagePath(imagePath: String?, onSuccess: (() -> Unit)? = null, onError: (() -> Unit)? = null) {
    load(Picasso.get().load(imagePath), onSuccess, onError)
}

internal fun ImageView.loadImageFile(imageFile: File, onSuccess: (() -> Unit)? = null, onError: (() -> Unit)? = null) {
    load(Picasso.get().load(imageFile), onSuccess, onError)
}

internal fun ImageView.loadImageResourceId(@DrawableRes imageResourceId: Int, onSuccess: (() -> Unit)? = null, onError: (() -> Unit)? = null) {
    load(Picasso.get().load(imageResourceId), onSuccess, onError)
}

private fun ImageView.load(requestCreator: RequestCreator, onSuccess: (() -> Unit)? = null, onError: (() -> Unit)? = null) {
    requestCreator.into(this, object : Callback {
        override fun onSuccess() {
            onSuccess?.invoke()
        }

        override fun onError(e: Exception?) {
            onError?.invoke()
        }
    })
}
