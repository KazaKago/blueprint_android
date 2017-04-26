package com.kazakago.cleanarchitecture.presentation.view.view

import android.content.Context
import android.support.v7.appcompat.R
import android.support.v7.widget.AppCompatSpinner
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView

/**
 * Perform 'onItemSelected' event, only user selected Spinner.
 */
class UserSelectSpinner @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = R.attr.spinnerStyle) : AppCompatSpinner(context, attrs, defStyle) {

    private var isUserOpen = false
    private var listener: AdapterView.OnItemSelectedListener? = null

    init {
        registerEvents()
    }

    private fun registerEvents() {
        super.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (isUserOpen) {
                    listener?.onItemSelected(parent, view, position, id)
                    isUserOpen = false
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                listener?.onNothingSelected(parent)
            }
        })
    }

    override fun setOnItemSelectedListener(listener: AdapterView.OnItemSelectedListener?) {
        this.listener = listener
    }

    override fun performClick(): Boolean {
        isUserOpen = true
        return super.performClick()
    }

}