package com.kazakago.cleanarchitecture.presentation.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.listener.fragment.AboutFragmentViewModelListener
import com.kazakago.cleanarchitecture.presentation.presenter.fragment.AboutFragmentViewModel
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment(), AboutFragmentViewModelListener {

    companion object {
        fun createInstance(): AboutFragment {
            return AboutFragment()
        }
    }

    private lateinit var viewModel: AboutFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, AboutFragmentViewModel.Factory(requireActivity().application)).get(AboutFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)
        viewModel.listener = this
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.versionText.observe(this, Observer {
            versionTextView.text = it
        })
        viewModel.developByText.observe(this, Observer {
            developByTextView.text = it
        })
        viewModel.copyrightText.observe(this, Observer {
            copyrightTextView.text = it
        })
        playStoreLayout.setOnClickListener {
            viewModel.onClickPlayStore()
        }
        webSiteLayout.setOnClickListener {
            viewModel.onClickWebSite()
        }
        mailLayout.setOnClickListener {
            viewModel.onClickMail()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.listener = null
    }

    /* AboutFragmentViewModelListener */

    override fun openActionView(uri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    override fun openSendTo(uri: Uri) {
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        startActivity(intent)
    }

}
