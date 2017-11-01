package com.kazakago.cleanarchitecture.presentation.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kazakago.cleanarchitecture.databinding.FragmentAboutBinding
import com.kazakago.cleanarchitecture.presentation.listener.presenter.fragment.AboutFragmentViewModelListener
import com.kazakago.cleanarchitecture.presentation.presenter.fragment.AboutFragmentViewModel

class AboutFragment : Fragment(), AboutFragmentViewModelListener {

    companion object {
        @JvmStatic
        fun createInstance(): AboutFragment = AboutFragment()
    }

    private val viewModel: AboutFragmentViewModel by lazy { AboutFragmentViewModel(context = activity, listener = this) }
    private lateinit var binding: FragmentAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate(savedInstanceState = savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAboutBinding.inflate(inflater!!, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    /* AboutFragmentViewModelListener */

}
