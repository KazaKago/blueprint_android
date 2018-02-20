package com.kazakago.cleanarchitecture.presentation.view.adapter

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.ViewGroup

abstract class AbsItemViewHolder<T>(context: Context, parent: ViewGroup, @LayoutRes layoutResId: Int) : AbsViewHolder(context, parent, layoutResId) {

    var item: T? = null
        set(value) {
            onSetItem(value)
            field = value
        }

    protected abstract fun onSetItem(item: T?)

}