package com.kazakago.cleanarchitecture.presentation.view.hierarchy.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.kazakago.cleanarchitecture.presentation.view.R
import com.kazakago.cleanarchitecture.presentation.view.databinding.DialogForecastDescriptionBinding
import com.kazakago.cleanarchitecture.presentation.view.global.extension.formattedText
import com.kazakago.cleanarchitecture.presentation.viewmodel.hierarchy.forecast.ForecastViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ForecastDescriptionDialog : BottomSheetDialogFragment() {

    companion object {
        fun createInstance(): ForecastDescriptionDialog {
            return ForecastDescriptionDialog()
        }
    }

    private val viewModel by sharedViewModel<ForecastViewModel>()
    private var _binding: DialogForecastDescriptionBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogForecastDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.weather.observe(viewLifecycleOwner) {
            binding.publishDateTextView.text = getString(R.string.public_time, it.publicTime.formattedText(requireActivity()))
            binding.descriptionTextView.text = it.description.text
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) binding.loadingProgressBar.show() else binding.loadingProgressBar.hide()
        }
        viewModel.showError.observe(viewLifecycleOwner) {
            showExceptionSnackbar(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showExceptionSnackbar(exception: Exception) {
        Snackbar.make(binding.root, exception.localizedMessage ?: "", Snackbar.LENGTH_LONG).show()
    }

}
