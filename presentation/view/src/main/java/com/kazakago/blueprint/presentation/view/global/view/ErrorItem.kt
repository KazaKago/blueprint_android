package com.kazakago.blueprint.presentation.view.global.view

import android.view.View
import com.kazakago.blueprint.presentation.view.R
import com.kazakago.blueprint.presentation.view.databinding.ItemErrorBinding
import com.xwray.groupie.viewbinding.BindableItem

data class ErrorItem(private val exception: Exception) : BindableItem<ItemErrorBinding>(exception.hashCode().toLong()) {

    var onRetry: (() -> Unit) = {}

    override fun getLayout(): Int {
        return R.layout.item_error
    }

    override fun initializeViewBinding(view: View): ItemErrorBinding {
        return ItemErrorBinding.bind(view)
    }

    override fun bind(viewBinding: ItemErrorBinding, position: Int) {
        viewBinding.errorTextView.text = exception.toString()
        viewBinding.retryButton.setOnClickListener {
            onRetry()
        }
    }
}
