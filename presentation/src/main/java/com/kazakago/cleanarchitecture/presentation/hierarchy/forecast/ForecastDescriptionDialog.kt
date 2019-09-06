package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.global.extension.formattedDateTimeText
import kotlinx.android.synthetic.main.dialog_forecast_description.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ForecastDescriptionDialog : BottomSheetDialogFragment() {

    companion object {
        fun createInstance(): ForecastDescriptionDialog {
            return ForecastDescriptionDialog()
        }
    }

    private val viewModel by sharedViewModel<ForecastViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_forecast_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.weather.observe(viewLifecycleOwner) {
            publishDateTextView.text = getString(R.string.public_time, it.description.publicTime.formattedDateTimeText(requireActivity()))
            descriptionTextView.text = it.description.text
        }
    }

}
