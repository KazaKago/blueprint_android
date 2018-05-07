package com.kazakago.cleanarchitecture.presentation.hierarchy.about

import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kazakago.cleanarchitecture.presentation.R
import kotlinx.android.synthetic.main.fragment_about.*
import org.koin.android.architecture.ext.viewModel

class AboutFragment : Fragment() {

    companion object {
        fun createInstance(): AboutFragment {
            return AboutFragment()
        }
    }

    private val viewModel by viewModel<AboutFragmentViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
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
        viewModel.openActionView.observe(this, Observer {
            it?.let { openActionView(it) }
        })
        viewModel.openSendTo.observe(this, Observer {
            it?.let { openSendTo(it) }
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

    private fun openActionView(uri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun openSendTo(uri: Uri) {
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        startActivity(intent)
    }

}
