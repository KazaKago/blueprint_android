package com.kazakago.cleanarchitecture.presentation.listener.presenter.fragment

interface MainFragmentViewModelListener {

    fun setActionBarTitle(title: String?)

    fun setCitySpinnerSelection(position: Int)

}
