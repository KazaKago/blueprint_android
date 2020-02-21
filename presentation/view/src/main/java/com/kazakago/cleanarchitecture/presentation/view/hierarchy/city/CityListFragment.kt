package com.kazakago.cleanarchitecture.presentation.view.hierarchy.city

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.view.R
import com.kazakago.cleanarchitecture.presentation.view.databinding.FragmentCityListBinding
import com.kazakago.cleanarchitecture.presentation.viewmodel.global.livedata.liveevent.observe
import com.kazakago.cleanarchitecture.presentation.viewmodel.hierarchy.city.CityListViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityListFragment : Fragment() {

    private val cityRecyclerAdapter = GroupAdapter<GroupieViewHolder>()
    private var _binding: FragmentCityListBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<CityListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
        viewModel.showError.observe(viewLifecycleOwner, "") {
            showExceptionSnackbar(it)
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
        val action = CityListFragmentDirections.actionCityListFragmentToOssLicensesMenuFragment()
        findNavController().navigate(action)
    }

}
