package com.kazakago.cleanarchitecture.presentation.listener.presenter.fragment

/**
 * Main Fragment ViewModel Listener
 *
 * Created by tamura_k on 2016/06/02.
 */
interface MainFragmentViewModelListener {

    fun setActionBarTitle(title: String)

    fun setCitySpinnerSelection(position: Int)

}
