package com.kazakago.cleanarchitecture.presentation.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.kazakago.cleanarchitecture.R
import com.kazakago.cleanarchitecture.presentation.extension.formattedText
import com.kazakago.cleanarchitecture.presentation.listener.fragment.MainFragmentViewModelListener
import com.kazakago.cleanarchitecture.presentation.presenter.fragment.MainFragmentViewModel
import com.kazakago.cleanarchitecture.presentation.view.adapter.CitySpinnerAdapter
import com.kazakago.cleanarchitecture.presentation.view.adapter.ForecastRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), MainFragmentViewModelListener {

    interface Listener {
        fun setActionBarTitle(title: String?)
    }

    companion object {
        fun createInstance(): MainFragment = MainFragment()
    }

    private val viewModel by lazy { ViewModelProviders.of(this).get(MainFragmentViewModel::class.java) }
    private lateinit var citySpinnerAdapter: CitySpinnerAdapter
    private lateinit var forecastRecyclerAdapter: ForecastRecyclerAdapter
    private var listener: Listener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? Listener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        viewModel.listener = this
        fragmentManager
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        citySpinnerAdapter = CitySpinnerAdapter(activity!!)
        citySpinner.adapter = citySpinnerAdapter

        forecastRecyclerAdapter = ForecastRecyclerAdapter(activity!!)
        forecastRecyclerAdapter.listener = viewModel
        forecastRecyclerView.adapter = forecastRecyclerAdapter

        viewModel.cityList.observe(this, Observer {
            citySpinnerAdapter.cityList = it ?: listOf()
            citySpinnerAdapter.notifyDataSetChanged()
        })
        viewModel.selectedPosition.observe(this, Observer {
            it?.let {
                citySpinner.setSelection(it)
                listener?.setActionBarTitle(citySpinnerAdapter.cityList[it].name)
            }
        })
        viewModel.weather.observe(this, Observer {
            areaTextView.text = it?.location?.area
            prefectureTextView.text = it?.location?.prefecture
            cityTextView.text = it?.location?.city
            publicTimeTextView.text = getString(R.string.public_time, it?.publicTime?.formattedText(context!!) ?: "")
            forecastRecyclerAdapter.forecastList = it?.forecasts ?: listOf()
            forecastRecyclerAdapter.notifyDataSetChanged()
        })
        refreshButton.setOnClickListener {
            viewModel.onClickRefresh()
        }
        citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                viewModel.onCitySelected(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.listener = null
    }

    /* MainFragmentViewModelListener */

    override fun showToast(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

}
