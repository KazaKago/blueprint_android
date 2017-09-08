package com.kazakago.cleanarchitecture.presentation.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kazakago.cleanarchitecture.databinding.FragmentAboutBinding
import com.kazakago.cleanarchitecture.presentation.listener.presenter.fragment.AboutFragmentViewModelListener
import com.kazakago.cleanarchitecture.presentation.presenter.fragment.AboutFragmentViewModel

/**
 * About Fragment

 * @author Kensuke
 */
class AboutFragment : Fragment(), AboutFragmentViewModelListener {

    companion object {
        @JvmStatic
        fun newInstance(): AboutFragment = AboutFragment()
    }

    private lateinit var viewModel: AboutFragmentViewModel
    private var binding: FragmentAboutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = AboutFragmentViewModel(context = activity)
        viewModel.listener = this

        viewModel.onCreate(savedInstanceState = savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (binding == null) {
            binding = FragmentAboutBinding.inflate(inflater, container, false)
            binding?.viewModel = viewModel
        }

        return binding?.root
    }

    /* AboutFragmentViewModelListener */

}
