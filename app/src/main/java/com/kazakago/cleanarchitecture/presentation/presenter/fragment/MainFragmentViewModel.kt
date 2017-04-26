package com.kazakago.cleanarchitecture.presentation.presenter.fragment

import android.content.Context
import android.databinding.ObservableField
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.kazakago.cleanarchitecture.CleanApplication
import com.kazakago.cleanarchitecture.R
import com.kazakago.cleanarchitecture.domain.model.city.CityModel
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
import io.reactivex.rxkotlin.subscribeBy
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

    enum class Key {
        CITY_LIST,
        WEATHER,
        SELECTED_POSITION,
    }

    var area = ObservableField<String>()
    var prefecture = ObservableField<String>()
    var city = ObservableField<String>()
    var publicTime = ObservableField<String>()
    var citySpinnerAdapter = ObservableField<CitySpinnerAdapter>(CitySpinnerAdapter(context))
    var forecastRecyclerAdapter = ObservableField<ForecastRecyclerAdapter>(ForecastRecyclerAdapter(context))

    @Inject
    lateinit var weatherUseCase: WeatherUseCase
    @Inject
    lateinit var cityUseCase: CityUseCase
    var listener: MainFragmentViewModelListener? = null
    private var compositeDisposable: CompositeDisposable? = null
    var cityList: ArrayList<CityModel>? = null
    var weather: WeatherModel? = null
    var selectedPosition: Int = 0

    init {
        CleanApplication.applicationComponent.inject(this)
        forecastRecyclerAdapter.get().listener = this
    }

    fun onCreate(savedInstanceState: Bundle?) {
        compositeDisposable = CompositeDisposable()
        savedInstanceState?.let {
            cityList = it.getParcelableArrayList<CityModel>(Key.CITY_LIST.name)
            weather = it.getParcelable(Key.WEATHER.name)
            selectedPosition = it.getInt(Key.SELECTED_POSITION.name)
        }
    }

    fun onCreateView(savedInstanceState: Bundle?) {
        cityList?.let {
            refreshCityList()
            weather?.let {
                refreshWeather()
            } ?: run {
                fetchWeather()
            }
        } ?: run {
            fetchCityList {
                fetchWeather()
            }
        }
    }

    fun onDestroy() {
        compositeDisposable?.dispose()
    }

    fun onSaveInstanceState(outState: Bundle?) {
        outState?.putParcelableArrayList(Key.CITY_LIST.name, cityList)
        outState?.putParcelable(Key.WEATHER.name, weather)
        outState?.putInt(Key.SELECTED_POSITION.name, selectedPosition)
    }

    fun onClickRefresh(view: View?) {
        fetchWeather()
    }

    fun onCitySelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedPosition = position
        fetchWeather()
    }

    private fun fetchCityList(completion: (() -> Unit)? = null) {
        compositeDisposable?.add(cityUseCase.findAll()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribeBy(
                        onSuccess = {
                            cityList = ArrayList(it)
                            refreshCityList()
                            completion?.invoke()
                        },
                        onError = {
                            showToast(it.localizedMessage)
                            completion?.invoke()
                        }
                ))
    }

    private fun refreshCityList() {
        cityList?.map { CityViewModel(context, it) }?.let {
            citySpinnerAdapter.get().cityViewModelList = it
            citySpinnerAdapter.get().notifyDataSetChanged()
            Handler().post { listener?.setCitySpinnerSelection(selectedPosition) }
        }
    }

    private fun fetchWeather(completion: (() -> Unit)? = null) {
        cityList?.get(selectedPosition)?.id?.let {
            compositeDisposable?.add(weatherUseCase.fetch(it)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = {
                                weather = it
                                refreshWeather()
                                showToast(context.getString(R.string.refresh_complete))
                                completion?.invoke()
                            },
                            onError = {
                                refreshWeather()
                                showToast(it.localizedMessage)
                                completion?.invoke()
                            }
                    ))
        }
    }

    private fun refreshWeather() {
        weather?.let {
            area.set(it.location?.area)
            prefecture.set(it.location?.prefecture)
            city.set(it.location?.city)
            publicTime.set(context.getString(R.string.public_time, formattedTime(it.publicTime)))
            forecastRecyclerAdapter.get().forecastList = it.forecasts.map { ForecastViewModel(context, it) }
            forecastRecyclerAdapter.get().notifyDataSetChanged()
            listener?.setActionBarTitle(it.title)
        }
    }

    private fun formattedTime(timeStr: String?): String? {
        return timeStr?.let {
            try {
                val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSSZ", Locale.getDefault())
                return formattedTime(formatter.parse(it).time)
            } catch (e: ParseException) {
                null
            }
        }
    }

    private fun formattedTime(timestamp: Long): String {
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
