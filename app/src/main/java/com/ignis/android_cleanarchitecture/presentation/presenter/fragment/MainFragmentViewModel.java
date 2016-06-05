package com.ignis.android_cleanarchitecture.presentation.presenter.fragment;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ignis.android_cleanarchitecture.CleanApplication;
import com.ignis.android_cleanarchitecture.R;
import com.ignis.android_cleanarchitecture.domain.model.WeatherModel;
import com.ignis.android_cleanarchitecture.domain.repository.WeatherRepository;
import com.ignis.android_cleanarchitecture.domain.usecase.WeatherUseCase;
import com.ignis.android_cleanarchitecture.presentation.listener.fragment.MainFragmentListener;
import com.ignis.android_cleanarchitecture.presentation.presenter.adapter.WeatherViewModel;

import java.util.List;

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

    private Context context;
    public ObservableField<String> title;
    private MainFragmentListener mainFragmentListener;
    private CompositeSubscription subscriptions;
    private Realm realm;
    private RealmChangeListener<Realm> realmChangeListener;

    public MainFragmentViewModel(Context context, MainFragmentListener mainFragmentListener) {
        CleanApplication.getInstance(context).getApplicationComponent().inject(this);
        this.context = context;
        this.title = new ObservableField<>();
        this.mainFragmentListener = mainFragmentListener;
        this.realmChangeListener = element -> refreshView();
    }

    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
//            downloadWeather();
        }
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
        subscriptions.add(weatherUseCase.download(400040)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        weatherModel -> showToast(context.getString(R.string.refresh_complete)),
                        error -> showToast(error.getLocalizedMessage()),
                        () -> {
                        }));
    }

    private WeatherModel getWeather() {
        return weatherRepository.find(realm, 400040);
    }

    private void refreshView() {
        WeatherModel weather = getWeather();
        if (weather != null) {
            title.set(weather.getTitle());
            mainFragmentListener.onGetWeather(getForecasts(weather));
        }
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
