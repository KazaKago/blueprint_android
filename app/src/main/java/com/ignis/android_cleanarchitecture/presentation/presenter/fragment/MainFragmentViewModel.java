package com.ignis.android_cleanarchitecture.presentation.presenter.fragment;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.ignis.android_cleanarchitecture.CleanApplication;
import com.ignis.android_cleanarchitecture.data.repository.WeatherRepositoryImpl;
import com.ignis.android_cleanarchitecture.domain.model.WeatherModel;
import com.ignis.android_cleanarchitecture.domain.repository.WeatherRepository;
import com.ignis.android_cleanarchitecture.domain.usecase.WeatherUseCase;
import com.ignis.android_cleanarchitecture.presentation.listener.fragment.MainFragmentListener;
import com.ignis.android_cleanarchitecture.presentation.presenter.adapter.ProfileViewModel;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmChangeListener;
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

    private Context context;
    private MainFragmentListener mainFragmentListener;
    private CompositeSubscription subscriptions;
    private Realm realm;
    private RealmChangeListener<Realm> realmChangeListener;

    public MainFragmentViewModel(Context context, MainFragmentListener mainFragmentListener) {
        CleanApplication.getInstance(context).getApplicationComponent().inject(this);
        this.context = context;
        this.mainFragmentListener = mainFragmentListener;
        this.realmChangeListener = element -> mainFragmentListener.onGetWeather(new ProfileViewModel(context, getWeather()));
    }

    public void onStart() {
        subscriptions = new CompositeSubscription();
        realm = Realm.getDefaultInstance();
        realm.addChangeListener(realmChangeListener);

        WeatherModel weatherModel = getWeather();
        if (weatherModel != null) {
            mainFragmentListener.onGetWeather(new ProfileViewModel(context, weatherModel));
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

    public void downloadWeather() {
        subscriptions.add(weatherUseCase.download(400040)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        weatherModel -> showToast("Complete Refresh."),
                        error -> showToast(error.getLocalizedMessage()),
                        () -> {
                        }));
    }

    public WeatherModel getWeather() {
        WeatherRepository weatherRepository = new WeatherRepositoryImpl(context);
        return weatherRepository.find(realm, 400040);
    }


    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
