package com.kazakago.cleanarchitecture.presentation.global.viewholder

import android.content.Context
import android.view.View
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

abstract class ItemHolder(id: Long = 0) : Item(id) {

    protected lateinit var context: Context

    @Deprecated(
        message = "Don't use this method in ItemHolder",
        level = DeprecationLevel.HIDDEN
    )
    override fun createViewHolder(itemView: View): ViewHolder {
        context = itemView.context
        return super.createViewHolder(itemView)
    }

}
