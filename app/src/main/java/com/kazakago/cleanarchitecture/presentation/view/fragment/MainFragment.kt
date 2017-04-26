package com.kazakago.cleanarchitecture.presentation.view.fragment

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kazakago.cleanarchitecture.R
import com.kazakago.cleanarchitecture.databinding.FragmentMainBinding
import com.kazakago.cleanarchitecture.presentation.listener.presenter.fragment.MainFragmentViewModelListener
import com.kazakago.cleanarchitecture.presentation.listener.view.fragment.MainFragmentListener
import com.kazakago.cleanarchitecture.presentation.presenter.fragment.MainFragmentViewModel

/**
 * Main Fragment
 *
 * @author Kensuke
 */
class MainFragment : Fragment(), MainFragmentViewModelListener {

    companion object {
        @JvmStatic
        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            return fragment
        }
    }

    private var binding: FragmentMainBinding? = null
    private lateinit var viewModel: MainFragmentViewModel
    private var mainFragmentListener: MainFragmentListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            mainFragmentListener = context as MainFragmentListener?
        } catch (e: ClassCastException) {
            throw ClassCastException(context?.toString() + " must implement " + MainFragmentListener::class.java.simpleName)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainFragmentViewModel(activity)
        viewModel.listener = this

        viewModel.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (binding == null) {
            binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false)
            binding?.viewModel = viewModel
        }
        viewModel.onCreateView(savedInstanceState)
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        viewModel.onSaveInstanceState(outState)
    }

    /* MainFragmentViewModelListener */

    override fun setActionBarTitle(title: String) {
        mainFragmentListener?.setActionBarTitle(title)
    }

    override fun setCitySpinnerSelection(position: Int) {
        binding?.citySpinner?.setSelection(position)
    }

}
