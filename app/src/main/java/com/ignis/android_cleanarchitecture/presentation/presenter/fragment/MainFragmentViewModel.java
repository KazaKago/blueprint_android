package com.ignis.android_cleanarchitecture.presentation.presenter.fragment;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import com.ignis.android_cleanarchitecture.CleanApplication;
import com.ignis.android_cleanarchitecture.R;
import com.ignis.android_cleanarchitecture.domain.model.LocationModel;
import com.ignis.android_cleanarchitecture.domain.model.WeatherModel;
import com.ignis.android_cleanarchitecture.domain.repository.WeatherRepository;
import com.ignis.android_cleanarchitecture.domain.usecase.WeatherUseCase;
import com.ignis.android_cleanarchitecture.presentation.listener.fragment.MainFragmentListener;
import com.ignis.android_cleanarchitecture.presentation.presenter.adapter.WeatherViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmChangeListener;
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
    public WeatherRepository weatherRepository;

    public ObservableField<String> area;
    public ObservableField<String> prefecture;
    public ObservableField<String> city;
    public ObservableField<String> publicTime;

    private Context context;
    private MainFragmentListener mainFragmentListener;
    private CompositeSubscription subscriptions;
    private Realm realm;
    private RealmChangeListener<Realm> realmChangeListener;

    public MainFragmentViewModel(Context context, MainFragmentListener mainFragmentListener) {
        CleanApplication.getInstance(context).getApplicationComponent().inject(this);
        this.context = context;
        this.area = new ObservableField<>();
        this.prefecture = new ObservableField<>();
        this.city = new ObservableField<>();
        this.publicTime = new ObservableField<>();
        this.mainFragmentListener = mainFragmentListener;
        this.realmChangeListener = element -> refreshView();
    }

    public void onStart() {
        subscriptions = new CompositeSubscription();
        realm = Realm.getDefaultInstance();
        realm.addChangeListener(realmChangeListener);

        refreshView();
    }

    public void onStop() {
        realm.removeChangeListener(realmChangeListener);
        realm.close();
        subscriptions.unsubscribe();
    }

    public void onClickRefresh(View view) {
        downloadWeather();
    }

    private void downloadWeather() {
        subscriptions.add(weatherUseCase.download(130010)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        weatherModel -> showToast(context.getString(R.string.refresh_complete)),
                        error -> showToast(error.getLocalizedMessage()),
                        () -> {
                        }));
    }

    private WeatherModel getWeather() {
        return weatherRepository.find(realm, 130010);
    }

    private void refreshView() {
        WeatherModel weather = getWeather();
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
            mainFragmentListener.onGetWeather(getForecasts(weather));
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

    private List<WeatherViewModel> getForecasts(WeatherModel weather) {
        return Observable.from(weather.getForecasts())
                .map(forecastModel -> new WeatherViewModel(context, forecastModel))
                .toList()
                .toBlocking().single();
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
