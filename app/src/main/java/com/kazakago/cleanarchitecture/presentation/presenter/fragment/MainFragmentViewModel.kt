package com.kazakago.cleanarchitecture.presentation.presenter.fragment

import android.content.Context
import android.databinding.ObservableField
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.evernote.android.state.State
import com.evernote.android.state.StateSaver
import com.kazakago.cleanarchitecture.CleanApplication
import com.kazakago.cleanarchitecture.R
import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel
import com.kazakago.cleanarchitecture.domain.usecase.CityUseCase
import com.kazakago.cleanarchitecture.domain.usecase.WeatherUseCase
import com.kazakago.cleanarchitecture.presentation.listener.presenter.fragment.MainFragmentViewModelListener
import com.kazakago.cleanarchitecture.presentation.listener.view.adapter.ForecastRecyclerAdapterListener
import com.kazakago.cleanarchitecture.presentation.presenter.adapter.CityViewModel
import com.kazakago.cleanarchitecture.presentation.presenter.adapter.ForecastViewModel
import com.kazakago.cleanarchitecture.presentation.view.adapter.CitySpinnerAdapter
import com.kazakago.cleanarchitecture.presentation.view.adapter.ForecastRecyclerAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Main Fragment ViewModel
 *
 * @author Kensuke
 */
class MainFragmentViewModel(private val context: Context) : ForecastRecyclerAdapterListener {

    var area = ObservableField<String>()
    var prefecture = ObservableField<String>()
    var city = ObservableField<String>()
    var publicTime = ObservableField<String>()
    var citySpinnerAdapter = ObservableField<CitySpinnerAdapter>(CitySpinnerAdapter(context))
    var forecastRecyclerAdapter = ObservableField<ForecastRecyclerAdapter>(ForecastRecyclerAdapter(context))

    private var compositeDisposable: CompositeDisposable? = null

    var listener: MainFragmentViewModelListener? = null

    @Inject
    lateinit var weatherUseCase: WeatherUseCase
    @Inject
    lateinit var cityUseCase: CityUseCase

    @State
    var selectedPosition: Int = 0

    init {
        CleanApplication.applicationComponent.inject(this)
    }

    fun onCreate(savedInstanceState: Bundle?) {
        StateSaver.restoreInstanceState(this, savedInstanceState)
        forecastRecyclerAdapter.get().setForecastRecyclerAdapterListener(this)
        refreshCityView()
    }

    fun onCreateView(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            Handler().post { listener?.setCitySpinnerSelection(selectedPosition) }
        }
    }

    fun onStart() {
        compositeDisposable = CompositeDisposable()
        refreshWeather()
    }

    fun onStop() {
        compositeDisposable?.dispose()
    }

    fun onSaveInstanceState(outState: Bundle?) {
        outState?.let { StateSaver.saveInstanceState(this, it) }
    }

    fun onClickRefresh(view: View) {
        fetchWeather()
    }

    fun onCitySelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        selectedPosition = position
        fetchWeather()
    }

    private fun fetchWeather() {
        val cityId = citySpinnerAdapter.get().getItem(selectedPosition).id.get()
        compositeDisposable?.add(weatherUseCase.fetch(cityId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { weather ->
                            refreshWeather(weather)
                            showToast(context.getString(R.string.refresh_complete))
                        }
                ) { error ->
                    refreshWeather()
                    showToast(error.localizedMessage)
                })
    }

    private fun refreshCityView() {
        val cityList = cityUseCase.findAll()
                .map { cityModel -> CityViewModel(context, cityModel) }
                .toList()
                .blockingGet()
        citySpinnerAdapter.get().cityViewModelList = cityList
    }

    private fun refreshWeather() {
        val cityId = citySpinnerAdapter.get().getItem(selectedPosition).id.get()
        refreshWeather(weatherUseCase.find(cityId))
    }

    private fun refreshWeather(weather: WeatherModel?) {
        area.set(weather?.location?.area)
        prefecture.set(weather?.location?.prefecture)
        city.set(weather?.location?.city)
        publicTime.set(context.getString(R.string.public_time, formattedTime(weather?.publicTime)))
        forecastRecyclerAdapter.get().setForecastViewModelList(weather?.forecasts?.map { ForecastViewModel(context, it) } ?: ArrayList())
        forecastRecyclerAdapter.get().notifyDataSetChanged()
        listener?.setActionBarTitle(weather?.title ?: "")
    }

    private fun formattedTime(timeStr: String?): String? {
        return timeStr?.let {
            try {
                val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSSZ", Locale.getDefault())
                return formattedTime(formatter.parse(it).time)
            } catch (e: ParseException) { null }
        }
    }

    private fun formattedTime(timestamp: Long?): String {
        val dateFormat = android.text.format.DateFormat.getDateFormat(context)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(context)
        return dateFormat.format(timestamp) + " " + timeFormat.format(timestamp)
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    /* ForecastRecyclerAdapterListener */

    override fun onItemClick(forecastViewModel: ForecastViewModel) {
        showToast(forecastViewModel.telop.get())
    }

}
