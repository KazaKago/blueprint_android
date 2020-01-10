package com.kazakago.cleanarchitecture.view.hierarchy.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.kazakago.cleanarchitecture.model.about.AppInfo
import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.state.StoreValue
import com.kazakago.cleanarchitecture.view.R
import com.kazakago.cleanarchitecture.view.databinding.FragmentAboutBinding
import com.kazakago.cleanarchitecture.view.hierarchy.city.CityRecyclerItem
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.liveevent.observe
import com.kazakago.cleanarchitecture.viewmodel.hierarchy.about.AboutViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class AboutFragment : Fragment() {

    companion object {
        fun createInstance(): AboutFragment {
            return AboutFragment()
        }
    }

    private val viewModel by sharedViewModel<AboutViewModel>()
    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.playStoreLayout.setOnClickListener {
            viewModel.onClickPlayStore()
        }
        binding.webSiteLayout.setOnClickListener {
            viewModel.onClickWebSite()
        }
        binding.mailLayout.setOnClickListener {
            viewModel.onClickMail()
        }

        viewModel.appInfo.observe(viewLifecycleOwner) {
            binding.versionTextView.text = getString(R.string.about_ver, it.versionName.value)
        }
        viewModel.developerInfo.observe(viewLifecycleOwner) {
            binding.copyrightTextView.text = getString(R.string.about_copyright, Calendar.getInstance().get(Calendar.YEAR), it.name)
            binding.developByTextView.text = getString(R.string.about_develop_by, it.name)
        }
        viewModel.openActionView.observe(viewLifecycleOwner, "") {
            openActionView(it)
        }
        viewModel.openSendTo.observe(viewLifecycleOwner, "") {
            openSendTo(it)
        }
    }

    private fun updateAppInfoState(cityListState: StoreState<AppInfo>) {
        when (cityListState) {
            is StoreState.Fixed -> {
                binding.loadingProgressBar.hide()
            }
            is StoreState.Loading -> {
                binding.loadingProgressBar.show()
            }
            is StoreState.Error -> {
                binding.loadingProgressBar.hide()
                showExceptionSnackbar(cityListState.exception)
            }
        }
        updateCityListValue(cityListState.value)
    }

    private fun updateCityListValue(cityListValue: StoreValue<List<City>>) {
        when (cityListValue) {
            is StoreValue.Stored -> {
                cityRecyclerAdapter.updateAsync(cityListValue.value.map {
                    CityRecyclerItem(it).apply {
                        onClickItem = { city ->
                            goForecastActivity(city.id)
                        }
                    }
                })
            }
            is StoreValue.NotStored -> {
                cityRecyclerAdapter.clear()
            }
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
