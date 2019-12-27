package com.kazakago.cleanarchitecture.view.hierarchy.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.state.StoreValue
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.view.R
import com.kazakago.cleanarchitecture.view.databinding.DialogForecastDescriptionBinding
import com.kazakago.cleanarchitecture.view.global.extension.formattedText
import com.kazakago.cleanarchitecture.viewmodel.hierarchy.forecast.ForecastViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ForecastDescriptionDialog : BottomSheetDialogFragment() {

    companion object {
        fun createInstance(): ForecastDescriptionDialog {
            return ForecastDescriptionDialog()
        }
    }

    private val viewModel by sharedViewModel<ForecastViewModel>()
    private lateinit var binding: DialogForecastDescriptionBinding
    private val exceptionSnackbar by lazy { Snackbar.make(binding.root, "", Snackbar.LENGTH_INDEFINITE) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogForecastDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.weather.observe(viewLifecycleOwner) {
            refreshState(it)
        }
    }

    private fun refreshState(weatherState: StoreState<Weather>) {
        when (weatherState) {
            is StoreState.Fixed -> {
                binding.loadingProgressBar.hide()
                exceptionSnackbar.setText("")
                exceptionSnackbar.dismiss()
            }
            is StoreState.Loading -> {
                binding.loadingProgressBar.show()
                exceptionSnackbar.setText("")
                exceptionSnackbar.dismiss()
            }
            is StoreState.Error -> {
                binding.loadingProgressBar.hide()
                exceptionSnackbar.setText(weatherState.exception.localizedMessage ?: "")
                exceptionSnackbar.show()
            }
        }
        updateWeather(weatherState.value)
    }

    private fun updateWeather(weatherValue: StoreValue<Weather>) {
        when (weatherValue) {
            is StoreValue.Stored -> {
                binding.publishDateTextView.text = getString(R.string.public_time, weatherValue.value.publicTime.formattedText(requireActivity()))
                binding.descriptionTextView.text = weatherValue.value.description.text
            }
            is StoreValue.NotStored -> {
                binding.publishDateTextView.text = ""
                binding.descriptionTextView.text = ""
            }
        }
    }


}
