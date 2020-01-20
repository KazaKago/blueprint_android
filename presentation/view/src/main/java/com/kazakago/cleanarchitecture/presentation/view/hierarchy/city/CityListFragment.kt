package com.kazakago.cleanarchitecture.presentation.view.hierarchy.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.presentation.view.databinding.FragmentCityListBinding
import com.kazakago.cleanarchitecture.presentation.view.hierarchy.forecast.ForecastActivity
import com.kazakago.cleanarchitecture.presentation.viewmodel.hierarchy.city.CityListViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CityListFragment : Fragment() {

    companion object {
        fun createInstance(): CityListFragment {
            return CityListFragment()
        }
    }

    private val viewModel by sharedViewModel<CityListViewModel>()
    private var _binding: FragmentCityListBinding? = null
    private val binding get() = _binding!!
    private val cityRecyclerAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCityListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cityRecyclerView.adapter = cityRecyclerAdapter

        viewModel.cityList.observe(viewLifecycleOwner) {
            updateCityList(it)
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

    private fun updateCityList(cityList: List<City>) {
        cityRecyclerAdapter.updateAsync(cityList.map {
            CityRecyclerItem(it).apply {
                onClickItem = { city ->
                    goForecastActivity(city.id)
                }
            }
        })
    }

    private fun showExceptionSnackbar(exception: Exception) {
        Snackbar.make(binding.root, exception.localizedMessage ?: "", Snackbar.LENGTH_LONG).show()
    }

    private fun goForecastActivity(cityId: CityId) {
        val intent = ForecastActivity.createIntent(requireActivity(), cityId)
        startActivity(intent)
    }

}
