package com.kazakago.cleanarchitecture.presentation.global.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class AbsViewHolder(protected val context: Context, parent: ViewGroup, @LayoutRes layoutResId: Int) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(layoutResId, parent, false)), LayoutContainer {

    override val containerView: View = itemView

}