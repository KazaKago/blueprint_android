package com.ignis.android_cleanarchitecture.presentation.presenter.fragment;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ignis.android_cleanarchitecture.CleanApplication;
import com.ignis.android_cleanarchitecture.R;
import com.ignis.android_cleanarchitecture.domain.model.city.CityModel;
import com.ignis.android_cleanarchitecture.domain.model.weather.ForecastModel;
import com.ignis.android_cleanarchitecture.domain.model.weather.LocationModel;
import com.ignis.android_cleanarchitecture.domain.model.weather.WeatherModel;
import com.ignis.android_cleanarchitecture.domain.usecase.CityUseCase;
import com.ignis.android_cleanarchitecture.domain.usecase.WeatherUseCase;
import com.ignis.android_cleanarchitecture.presentation.listener.fragment.MainFragmentListener;
import com.ignis.android_cleanarchitecture.presentation.presenter.adapter.CityViewModel;
import com.ignis.android_cleanarchitecture.presentation.presenter.adapter.ForecastViewModel;
import com.ignis.android_cleanarchitecture.presentation.view.adapter.CitySpinnerAdapter;
import com.ignis.android_cleanarchitecture.presentation.view.adapter.ForecastRecyclerAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Main Fragment ViewModel
 *
 * @author Kensuke
 */
public class MainFragmentViewModel {

    @Inject
    public WeatherUseCase weatherUseCase;
    @Inject
    public CityUseCase cityUseCase;

    public ObservableField<String> area;
    public ObservableField<String> prefecture;
    public ObservableField<String> city;
    public ObservableField<String> publicTime;
    public ObservableField<CitySpinnerAdapter> citySpinnerAdapter;
    public ObservableField<ForecastRecyclerAdapter> forecastRecyclerAdapter;

    private Context context;
    private int selectedCityId;
    private MainFragmentListener mainFragmentListener;
    private CompositeSubscription subscriptions;

    public MainFragmentViewModel(Context context, MainFragmentListener mainFragmentListener) {
        CleanApplication.getInstance(context).getApplicationComponent().inject(this);
        this.context = context;
        this.area = new ObservableField<>();
        this.prefecture = new ObservableField<>();
        this.city = new ObservableField<>();
        this.publicTime = new ObservableField<>();
        this.citySpinnerAdapter = new ObservableField<>(new CitySpinnerAdapter(context));
        this.forecastRecyclerAdapter = new ObservableField<>(new ForecastRecyclerAdapter(context));
        this.mainFragmentListener = mainFragmentListener;
    }

    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            List<CityModel> cityList = cityUseCase.findAll();
            citySpinnerAdapter.get().setCityViewModelList(getCityViewModelList(cityList));
            citySpinnerAdapter.get().notifyDataSetChanged();
            if (0 < cityList.size()) selectedCityId = cityList.get(0).getId();
        }
    }

    public void onStart() {
        subscriptions = new CompositeSubscription();
        refreshView();
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }

    public void onClickRefresh(View view) {
        fetchWeather();
    }

    public void onCitySelected(AdapterView<?> parent, View view, int position, long id) {
        selectedCityId = citySpinnerAdapter.get().getItem(position).id.get();
        fetchWeather();
    }

    private void fetchWeather() {
        subscriptions.add(weatherUseCase.fetch(selectedCityId)
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
        refreshView(weatherUseCase.find(selectedCityId));
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
            mainFragmentListener.setActionBarTitle(weather.getTitle());

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
                .map(CityViewModel::new)
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

}
