package com.ignis.android_cleanarchitecture.presentation.presenter.fragment;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.ignis.android_cleanarchitecture.CleanApplication;
import com.ignis.android_cleanarchitecture.domain.model.WeatherModel;
import com.ignis.android_cleanarchitecture.domain.repository.WeatherRepository;
import com.ignis.android_cleanarchitecture.domain.usecase.WeatherUseCase;
import com.ignis.android_cleanarchitecture.presentation.listener.fragment.MainFragmentListener;
import com.ignis.android_cleanarchitecture.presentation.presenter.adapter.PinpointLocationViewModel;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
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
    private MainFragmentListener mainFragmentListener;
    private CompositeSubscription subscriptions;
    private Realm realm;
    private RealmChangeListener<Realm> realmChangeListener;

    public MainFragmentViewModel(Context context, MainFragmentListener mainFragmentListener) {
        CleanApplication.getInstance(context).getApplicationComponent().inject(this);
        this.context = context;
        this.mainFragmentListener = mainFragmentListener;
        this.realmChangeListener = element -> {
            WeatherModel weatherModel = getWeather();
            if (weatherModel != null) mainFragmentListener.onGetWeather(getPinpointLocation(weatherModel));
        };
    }

    public void onStart() {
        subscriptions = new CompositeSubscription();
        realm = Realm.getDefaultInstance();
        realm.addChangeListener(realmChangeListener);

        WeatherModel weather = getWeather();
        if (weather != null) {
            mainFragmentListener.onGetWeather(getPinpointLocation(weather));
        } else {
            downloadWeather();
        }
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
                        weatherModel -> showToast("Complete Refresh."),
                        error -> showToast(error.getLocalizedMessage()),
                        () -> {
                        }));
    }

    private WeatherModel getWeather() {
        return weatherRepository.find(realm, 400040);
    }

    private List<PinpointLocationViewModel> getPinpointLocation(WeatherModel weather) {
        return Observable.from(weather.getPinpointLocation())
                .map(linkModel -> new PinpointLocationViewModel(context, linkModel))
                .toList()
                .toBlocking().single();
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
