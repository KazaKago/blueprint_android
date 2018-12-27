package com.kazakago.cleanarchitecture.presentation.global.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.LayoutRes

abstract class AbsItemViewHolder<T>(context: Context, parent: ViewGroup, @LayoutRes layoutResId: Int) : AbsViewHolder(context, parent, layoutResId) {

    var item: T? = null
        set(value) {
            onBind(value)
            field = value
        }

    protected abstract fun onBind(item: T?)

}