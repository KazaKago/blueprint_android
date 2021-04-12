package com.kazakago.cleanarchitecture.presentation.view.hierarchy.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.kazakago.cleanarchitecture.presentation.view.R
import com.kazakago.cleanarchitecture.presentation.view.databinding.FragmentAboutBinding
import com.kazakago.cleanarchitecture.presentation.viewmodel.global.extension.toUri
import com.kazakago.cleanarchitecture.presentation.viewmodel.hierarchy.about.AboutViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<AboutViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.playStoreLayout.setOnClickListener {
            viewModel.appInfo.value?.let { openActionView(it.playStoreUri.toUri()) }
        }
        binding.webSiteLayout.setOnClickListener {
            viewModel.developerInfo.value?.let { openActionView(it.siteUrl.toUri()) }
        }
        binding.mailLayout.setOnClickListener {
            viewModel.developerInfo.value?.let { openSendTo(it.mailAddress.toURI().toUri()) }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.appInfo.collect {
                if (it != null) binding.versionTextView.text = getString(R.string.about_ver, it.versionName.value)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.developerInfo.collect {
                if (it != null) {
                    binding.copyrightTextView.text = getString(R.string.about_copyright, Calendar.getInstance().get(Calendar.YEAR), it.name)
                    binding.developByTextView.text = getString(R.string.about_develop_by, it.name)
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.isLoading.collect {
                if (it) binding.loadingProgressBar.show() else binding.loadingProgressBar.hide()
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.showError.collect {
                showExceptionSnackbar(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showExceptionSnackbar(exception: Exception) {
        Snackbar.make(binding.root, exception.localizedMessage ?: "", Snackbar.LENGTH_LONG).show()
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
