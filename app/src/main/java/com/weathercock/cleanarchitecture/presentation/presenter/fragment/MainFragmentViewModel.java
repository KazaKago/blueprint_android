package com.weathercock.cleanarchitecture.presentation.presenter.fragment;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.weathercock.cleanarchitecture.CleanApplication;
import com.weathercock.cleanarchitecture.R;
import com.weathercock.cleanarchitecture.domain.model.city.CityModel;
import com.weathercock.cleanarchitecture.domain.model.weather.ForecastModel;
import com.weathercock.cleanarchitecture.domain.model.weather.LocationModel;
import com.weathercock.cleanarchitecture.domain.model.weather.WeatherModel;
import com.weathercock.cleanarchitecture.domain.usecase.CityUseCase;
import com.weathercock.cleanarchitecture.domain.usecase.WeatherUseCase;
import com.weathercock.cleanarchitecture.presentation.listener.presenter.fragment.MainFragmentViewModelListener;
import com.weathercock.cleanarchitecture.presentation.listener.view.adapter.ForecastRecyclerAdapterListener;
import com.weathercock.cleanarchitecture.presentation.presenter.adapter.CityViewModel;
import com.weathercock.cleanarchitecture.presentation.presenter.adapter.ForecastViewModel;
import com.weathercock.cleanarchitecture.presentation.view.adapter.CitySpinnerAdapter;
import com.weathercock.cleanarchitecture.presentation.view.adapter.ForecastRecyclerAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import icepick.Icepick;
import icepick.State;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Main Fragment ViewModel
 *
 * @author Kensuke
 */
public class MainFragmentViewModel implements ForecastRecyclerAdapterListener {

    public ObservableField<String> area;
    public ObservableField<String> prefecture;
    public ObservableField<String> city;
    public ObservableField<String> publicTime;
    public ObservableField<CitySpinnerAdapter> citySpinnerAdapter;
    public ObservableField<ForecastRecyclerAdapter> forecastRecyclerAdapter;

    private Context context;
    private MainFragmentViewModelListener mainFragmentViewModelListener;
    private CompositeSubscription subscriptions;

    @State
    public int selectedPosition;

    @Inject
    public WeatherUseCase weatherUseCase;
    @Inject
    public CityUseCase cityUseCase;

    public MainFragmentViewModel(Context context, MainFragmentViewModelListener mainFragmentViewModelListener) {
        CleanApplication.getInstance(context).getApplicationComponent().inject(this);
        this.area = new ObservableField<>();
        this.prefecture = new ObservableField<>();
        this.city = new ObservableField<>();
        this.publicTime = new ObservableField<>();
        CitySpinnerAdapter citySpinnerAdapter = new CitySpinnerAdapter(context);
        citySpinnerAdapter.setCityViewModelList(getCityViewModelList(cityUseCase.findAll()));
        this.citySpinnerAdapter = new ObservableField<>(citySpinnerAdapter);
        ForecastRecyclerAdapter forecastRecyclerAdapter = new ForecastRecyclerAdapter(context);
        forecastRecyclerAdapter.setForecastRecyclerAdapterListener(this);
        this.forecastRecyclerAdapter = new ObservableField<>(forecastRecyclerAdapter);
        this.mainFragmentViewModelListener = mainFragmentViewModelListener;
        this.context = context;
        this.selectedPosition = 0;
    }

    public void onCreate(Bundle savedInstanceState) {
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    public void onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            new Handler().post(() -> mainFragmentViewModelListener.setCitySpinnerSelection(selectedPosition));
        }
    }

    public void onStart() {
        subscriptions = new CompositeSubscription();
        refreshView();
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }

    public void onSaveInstanceState(Bundle outState) {
        Icepick.saveInstanceState(this, outState);
    }

    public void onClickRefresh(View view) {
        fetchWeather();
    }

    public void onCitySelected(AdapterView<?> parent, View view, int position, long id) {
        selectedPosition = position;
        fetchWeather();
    }

    private void fetchWeather() {
        String cityId = citySpinnerAdapter.get().getItem(selectedPosition).id.get();
        subscriptions.add(weatherUseCase.fetch(cityId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        weather -> {
                            refreshView(weather);
                            showToast(context.getString(R.string.refresh_complete));
                        },
                        error -> {
                            refreshView();
                            showToast(error.getLocalizedMessage());
                        },
                        () -> {
                        }
                ));
    }

    private void refreshView() {
        String cityId = citySpinnerAdapter.get().getItem(selectedPosition).id.get();
        refreshView(weatherUseCase.find(cityId));
    }

    private void refreshView(WeatherModel weather) {
        if (weather != null) {
            LocationModel location = weather.getLocation();
            if (location != null) {
                area.set(location.getArea());
                prefecture.set(location.getPrefecture());
                city.set(location.getCity());
            }
            try {
                publicTime.set(context.getString(R.string.public_time, getFormattedTime(weather.getPublicTime())));
            } catch (ParseException e) {
                e.printStackTrace();
                publicTime.set(null);
            }
            mainFragmentViewModelListener.setActionBarTitle(weather.getTitle());

            forecastRecyclerAdapter.get().setForecastViewModelList(getForecastViewModelList(weather.getForecasts()));
            forecastRecyclerAdapter.get().notifyDataSetChanged();
        }
    }

    private String getFormattedTime(String timeStr) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSSZ", Locale.getDefault());
        return getFormattedTime(formatter.parse(timeStr).getTime());
    }

    private String getFormattedTime(Long timestamp) {
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(context);
        return dateFormat.format(timestamp) + " " + timeFormat.format(timestamp);
    }

    private List<CityViewModel> getCityViewModelList(List<CityModel> cityList) {
        return Observable.from(cityList)
                .map(cityModel -> new CityViewModel(context, cityModel))
                .toList()
                .toBlocking()
                .single();
    }

    private List<ForecastViewModel> getForecastViewModelList(List<ForecastModel> forecastList) {
        return Observable.from(forecastList)
                .map(forecast -> new ForecastViewModel(context, forecast))
                .toList()
                .toBlocking()
                .single();
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /* ForecastRecyclerAdapterListener */

    @Override
    public void onItemClick(ForecastViewModel forecastViewModel) {
        showToast(forecastViewModel.telop.get());
    }

}
