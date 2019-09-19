package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.databinding.FragmentCityListBinding
import com.kazakago.cleanarchitecture.presentation.global.livedata.liveevent.observe
import com.kazakago.cleanarchitecture.presentation.hierarchy.forecast.ForecastActivity
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
    private lateinit var binding: FragmentCityListBinding
    private val cityRecyclerAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCityListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cityRecyclerView.adapter = cityRecyclerAdapter

        viewModel.cityList.observe(viewLifecycleOwner) {
            updateCityList(it)
        }
        viewModel.showError.observe(viewLifecycleOwner, "") {
            Toast.makeText(requireActivity(), it.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateCityList(cityList: List<City>) {
        cityRecyclerAdapter.updateAsync(cityList.map {
            CityRecyclerItem(it).apply {
                onClickItem = { city ->
                    goForecastActivity(city)
                }
            }
        })
    }

    private fun goForecastActivity(city: City) {
        val intent = ForecastActivity.createIntent(requireActivity(), city)
        startActivity(intent)
    }

}
