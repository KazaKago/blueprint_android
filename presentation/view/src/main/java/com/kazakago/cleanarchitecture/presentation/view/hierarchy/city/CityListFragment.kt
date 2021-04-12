package com.kazakago.cleanarchitecture.presentation.view.hierarchy.city

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.City
import com.kazakago.cleanarchitecture.presentation.view.R
import com.kazakago.cleanarchitecture.presentation.view.databinding.FragmentCityListBinding
import com.kazakago.cleanarchitecture.presentation.viewmodel.hierarchy.city.CityListViewModel
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityListFragment : Fragment() {

    private val cityRecyclerAdapter = GroupieAdapter()
    private var _binding: FragmentCityListBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<CityListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCityListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cityRecyclerView.adapter = cityRecyclerAdapter

        lifecycleScope.launchWhenStarted {
            viewModel.cityList.collect {
                updateCityList(it)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.city_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> goAbout()
            R.id.action_licenses -> goOssLicenses()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateCityList(cityList: List<City>) {
        cityRecyclerAdapter.updateAsync(cityList.map {
            CityRecyclerItem(it).apply {
                onClickItem = { city ->
                    goForecast(city)
                }
            }
        })
    }

    private fun showExceptionSnackbar(exception: Exception) {
        Snackbar.make(binding.root, exception.localizedMessage ?: "", Snackbar.LENGTH_LONG).show()
    }

    private fun goForecast(city: City) {
        val action = CityListFragmentDirections.actionCityListFragmentToForecastFragment(city.id, city.name)
        findNavController().navigate(action)
    }

    private fun goAbout() {
        val action = CityListFragmentDirections.actionCityListFragmentToAboutFragment()
        findNavController().navigate(action)
    }

    private fun goOssLicenses() {
        val action = CityListFragmentDirections.actionCityListFragmentToOssLicensesMenuActivity()
        findNavController().navigate(action)
    }

}
