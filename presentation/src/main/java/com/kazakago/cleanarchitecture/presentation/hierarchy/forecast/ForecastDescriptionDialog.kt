package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.databinding.DialogForecastDescriptionBinding
import com.kazakago.cleanarchitecture.presentation.global.extension.formattedDateTimeText
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ForecastDescriptionDialog : BottomSheetDialogFragment() {

    companion object {
        fun createInstance(): ForecastDescriptionDialog {
            return ForecastDescriptionDialog()
        }
    }

    private val viewModel by sharedViewModel<ForecastViewModel>()
    private lateinit var binding: DialogForecastDescriptionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogForecastDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.weather.observe(viewLifecycleOwner) {
            binding.publishDateTextView.text = getString(R.string.public_time, it.description.publicTime.formattedDateTimeText(requireActivity()))
            binding.descriptionTextView.text = it.description.text
        }
    }

}
