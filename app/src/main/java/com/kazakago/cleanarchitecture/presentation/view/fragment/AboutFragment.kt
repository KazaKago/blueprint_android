package com.kazakago.cleanarchitecture.presentation.view.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kazakago.cleanarchitecture.R
import com.kazakago.cleanarchitecture.databinding.FragmentAboutBinding
import com.kazakago.cleanarchitecture.presentation.listener.presenter.fragment.AboutFragmentViewModelListener
import com.kazakago.cleanarchitecture.presentation.presenter.fragment.AboutFragmentViewModel

/**
 * About Fragment

 * @author Kensuke
 */
class AboutFragment : Fragment(), AboutFragmentViewModelListener {

    companion object {
        fun newInstance(): AboutFragment {
            val fragment = AboutFragment()
            return fragment
        }
    }

    private var binding: FragmentAboutBinding? = null
    private lateinit var viewModel: AboutFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = AboutFragmentViewModel(activity)
        viewModel.listener = this
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (binding == null) {
            binding = DataBindingUtil.inflate<FragmentAboutBinding>(inflater, R.layout.fragment_about, container, false)
            binding?.viewModel = viewModel
        }
        return binding?.root
    }

    /* AboutFragmentViewModelListener */

}
