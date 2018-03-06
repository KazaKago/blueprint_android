package com.kazakago.cleanarchitecture.presentation.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.listener.fragment.ForecastFragmentViewModelListener
import com.kazakago.cleanarchitecture.presentation.presenter.fragment.ForecastFragmentViewModel
import com.kazakago.cleanarchitecture.presentation.view.adapter.ForecastRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_forecast.*

class ForecastFragment : Fragment(), ForecastFragmentViewModelListener {

    companion object {
        fun createInstance(city: City): ForecastFragment {
            val fragment = ForecastFragment()
            val bundle = Bundle()
            bundle.putSerializable(Key.City.name, city)
            fragment.arguments = bundle
            return fragment
        }
    }

    private enum class Key {
        City
    }

    private lateinit var viewModel: ForecastFragmentViewModel
    private lateinit var forecastRecyclerAdapter: ForecastRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val city = arguments?.getSerializable(Key.City.name) as City
        viewModel = ViewModelProviders.of(this, ForecastFragmentViewModel.Factory(requireActivity().application, city)).get(ForecastFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_forecast, container, false)
        viewModel.listener = this
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        forecastRecyclerAdapter = ForecastRecyclerAdapter(requireActivity())
        forecastRecyclerAdapter.listener = viewModel
        forecastRecyclerView.adapter = forecastRecyclerAdapter

        viewModel.weather.observe(this, Observer {
            forecastRecyclerAdapter.weather = it
            forecastRecyclerAdapter.notifyDataSetChanged()
        })
        viewModel.isLoading.observe(this, Observer {
            loadingProgressBar.visibility = if (it == true) View.VISIBLE else View.INVISIBLE
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.listener = null
    }

    /* ForecastFragmentViewModelListener */

    override fun showToast(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

}
