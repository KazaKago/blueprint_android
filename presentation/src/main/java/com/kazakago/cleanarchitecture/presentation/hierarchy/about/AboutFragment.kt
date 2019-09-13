package com.kazakago.cleanarchitecture.presentation.hierarchy.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.global.livedata.liveevent.observe
import kotlinx.android.synthetic.main.fragment_about.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class AboutFragment : Fragment(R.layout.fragment_about) {

    companion object {
        fun createInstance(): AboutFragment {
            return AboutFragment()
        }
    }

    private val viewModel by sharedViewModel<AboutViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playStoreLayout.setOnClickListener {
            viewModel.onClickPlayStore()
        }
        webSiteLayout.setOnClickListener {
            viewModel.onClickWebSite()
        }
        mailLayout.setOnClickListener {
            viewModel.onClickMail()
        }

        viewModel.appInfo.observe(viewLifecycleOwner) {
            versionTextView.text = getString(R.string.about_ver, it.versionName.value)
        }
        viewModel.developerInfo.observe(viewLifecycleOwner) {
            copyrightTextView.text = getString(R.string.about_copyright, Calendar.getInstance().get(Calendar.YEAR), it.name)
            developByTextView.text = getString(R.string.about_develop_by, it.name)
        }
        viewModel.openActionView.observe(viewLifecycleOwner, "") {
            openActionView(it)
        }
        viewModel.openSendTo.observe(viewLifecycleOwner, "") {
            openSendTo(it)
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
