package com.kazakago.cleanarchitecture.presentation.global.extension

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.ViewHolder.context(): Context {
    return itemView.context
}